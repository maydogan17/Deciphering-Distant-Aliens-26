# Deciphering-Distant-Aliens-26

## How To Run

<pre>
Firstly, you can run all the algorithms we included or only the algorithm we decided to be best.
Go to src->main->Main.java and there is a variable named TEST_ALL_ALGORITHMS, if you make its value true you can see the results of all algorithms.
If you make it false then you can see only the final algorithms result.

To run the program just run the Main.java

How to read the result:

The results are showed in table. In the rows there are different texts or sentences. In the columns there are different aliens.
Their intersection is a test.

For example;

An example output :

SkipList Algorithm

	          small aliens				               large aliens
text1	    Match: J, Elapsed Time(us): 310                 Match: No Match, Elapsed Time(us): 319
text2	    Match: No Match, Elapsed Time(us): 1238	    <b>Match: Lavender, Elapsed Time(us): 2189</b>
text3	    Match: No Match, Elapsed Time(us): 754	    Match: Albus, Elapsed Time(us): 2184
	          test aliens
text4	    Match: B, Elapsed Time(us): 110	

The bold test case is done by using text2 (or medium sized text, text1 is smallest one and text3 is biggest) and by using large aliens.
The result is Lavender which is indicated as match and the elapsed time in microseconds is given as 2189.

The bottom line is another test case which we come up with. This test case is used to check if algorithm works in overlapping words.
Overlapping words are described more in detail in the first thoughts section.

</pre>

## First thoughts

<pre>
Firstly, we tried to come up with an algorith which works without bothering about complexity. Then we thought a brute force algorithm. This algorithm works like below;

For each word:
  Start at the beginning of the text.
  If you find the word match continue checking after loosing consecutiveness and update the max number of consecutive word find.
  Then continue where it is left of.

This algorithm is a simple one but has some drawbacks. For example at index 5 we have 5 consecutive 3 character words. So algorithm looks till 5th index after it
finds a possible match it tracks the consecutive words. Yet it turns back to 6th index. So when it continues it will find another match at 8th index and it will 
look the last 4 consecutive ones again. But this is a redundancy and it may cause worst time complexity of O(n^2). Where n is the lenght of the text.

We will run this algorithm k times. K is the number of words. 
After these processes, which takes O(k.n^2) worst time complexity, we will have and array which stores maximum consecutive word amounts in the given text.
Then we can search this array in the given alien and word matrix and find the alien.
This search in the matrix process takes O(k.j) time. Where j is the number of aliens.

However, our implementation is not returning back to index after finding consecutive ones. When we first implemented it, we did not realize the problem. The
problem is we are not going back to next index and we are continuing the index after consecutive words. To clarify the situation think the example at the top. 
In described implementation we are turning back to 6th index. But in our implementation we are continuing with index 20. This later implementation is not correct.
But its worst time complexity is not O(n^2) instead O(n).

There is an example case where first implementation works and second not.
Word: abccba
Text: abccbabccbaabccba
The first implementation returns abccb<b>abccbaabccba</b>
The second implementation returns <b>abccba</b>bccba<b>abccba</b>

Edit: We fixed the algorithm and works fine with the all cases.
</pre>

## Divide and Conquer

<pre>
Can we use DC to improve the complexity and have an correct solution? We could not figure out how to divide the text meaningfully, merge it and get the most
consecutive word amount. However, while making brain storming we figured out how to solve a similiar problem with DC. The similiar problem is finding the most
consecutive pre-determined character in a text. We cannot find consecutive words, yet find characters using DC. 
So our reasoning is this; if we can modify our problem to more simpler one and solve it by DC, we may improve the complexity.

<b>Modification:</b>
  To ease the understanding we will describe a simple data structure, however it can also be mimicked by index manipulations.
  If we are searching a word with length m. We will going to divide the text into parts with length m. So we will have n/m parts. Call this parts as tokens.
  Then if a token equals to word it will be 1 and otherwise 0.
  So we end up modifying our problem 
  
<b>Division:</b>
  We can think 1's and 0's as characters. To find most consequtive 1's we can divide the text in the middle into two subtexts. Until we have one character left in
  the substring.
  
<b>Merge:</b>
  Then we will merge these two substrings. It can be done easily with O(1) worst time complexity. Yet we won't get into its details because we abondened this idea
  before starting becase of some problems. 

<b>Problem:</b>
  This token usage indeed in one way good and another way bad. Firstly, we cannot just skip characters because we can skip words. For example;
  Word: abc
  Text: abcbabcabc
  
  if we divide this into tokens as we mentioned. the tokens will be abc,bab,cab,c however if we divide the tokens starting from 1 left such as a,bcb,abc,abc as it 
  is seen we will have 2 consequtive abc's instead of 1. 
  
  So we can use this tokenization but also we need to take account these problems. To solve that, we can create more tokens starting from 1 character each turn
  until sliding to right becomes meaningless. If we have 3 length word after sliding to right 3 times, we will have same tokens so there is no need for that.
  
</pre>

## Improvements on Brute Force and Substring Algorithm

<pre>
This tokenization gave us an idea. Instead of using divide and conquer we can adapt tokenization to brute force. As we mentioned brute force is fast when it does 
not require to go back every time. When we use tokenization we can use this property of brute force. Because the side cases will be handled in different partitions
of text. Let us clarify the algorithm here; 

This algorithm is named as Substring.

Firstly we will have tokens and they are either 1 or 0. We can do this while running algorithm or before. This won't change much because in either case we need to 
check the tokens if they equal the word.

Please think the test case above. We will split text into tokens and each time we split the text again by sliding the start point 1 character right until reaching the word size which is 3.

1: abc,bab,cab,c => 1,0,0,0
2: a,bcb,abc,abc => 0,0,1,1
3: ab,cba,bca,bc => 0,0,0,0

If we run the brute force algorithm, which does not turn back while searching as it is mentioned above, in each these cases, then we will get the maximum
consequtive word numbers for each. Afterwards, we will choose the biggest one. 

This approach will solve the problem with intersecting words and problem with tokens.
  
</pre>

## Time Complexities of Substring and Divide and Conquer

<pre>
In each of these algorithms we will use tokenization technique. Yet finding the most consequtive ones in these tokens is different. 
We will have N/m tokens in first division and there will be m divisions so that we will have n tokens at total. N is length of text and m is length of word.

Divide and Conquer: The reccurence equations will be this T(n) = 2T(n/2) + O(1) where n is the total number of tokens which also equals to N.
This results in O(n) worst time complexity. 

Substring: The brute force will check each token which takes O(1) and there is O(n) tokens so it also has O(n) complexity.

If we think that we do this for every word and if we say that we have k number of words then the worst case time complexity is O(k.n).

We does not include the token matching time. Because we calculated that if there were 2 distinct characters instead of 4, then the expected comparisons per token
is 2. If we think it is 4 for text where 4 distinct characters used. The complexity will be multiplied with 4 which is constant and does not effect the complexity
much.
  
</pre>
  
## Skiplist Algorithm (not the real Skiplist)

<pre>
We pretty convinced that in pattern maching we cannot do better than O(n). However, we are not trying to find all the matches, we just wanted to find most
consequtive ones. Therefore we sought to ways to do better than O(k.n) in total. Skiplist algorithm inspired us to develop this algorithm. The idea here is 
doing some prework and use this prework to be able to skip some part of the text.

Firstly, we extracted the position list of each character in the text. This position list stores the positions of the character. 
This will take O(n) space and O(n) time. 

Then the algorithm is;
Look up the first character of the word.
Recurse in the characters position list.
Go to the position in the text. (Position is the value of list.)
Check if word matches.
If word matches than we put this position into matched position list

After recursion ends 
Find the most consequtive ones in the matched position list.

Example;
word : ab
text : abcbabca
a's position list : 0,4,7
recurse in the position list
matched position list : 0,4
max consequtive ones is 1

We use straightforward way to find the max consequtive number from position list. We first look the first position and check if 
the difference between position in index i (first position) and position in index i+1 is same with the lenght of word. 
Then we increase the consequtiveness counter. If it is not the same then we reset the counter. We do this in for loop.


This algorithm allows us to skip some of the text and it can be seen in the test runs. Yet it is not satisfying. 
The complexity of skiplist is overall same O(k.n) time complexity with extra O(n) space complexity. 

  
</pre>


## Better BruteForce

<pre>
After thinking about all of these we are inspired to develop more efficient bruteforce algorithm where we decrease the worst time complexity to
O(k.n).
In this algorithm we find the matches of words by iterating over the text one character by one and when we find the match, we store its position (index) into an array.
Then we use the technique mentioned over in SkipList part to find the max consequtive number of occurences.

We pay additional O(n) space complexity to decrease worst time complexity and it works slightly better than Substring algorithm.
  
</pre>

## Further Improvements
<pre>
We always talked about how to improve the algorithm which find the most consequtive words. Yet there is another part of the problem.
Which is finding the alien from the map.
We implemented the most straightforward algorithm which takes O(j.k) where j is the number of aliens and k is the number of words.
Which is way much smaller than O(k.n) so it does not change the resulting time complexity.
However we can improve it further. We can use perfect hash tables. Because we first read the aliens from the cvs and do not insert or 
delete items. It is possible. One can think that this maps value is an array with numbers but we can manipulate them and have a integer value map. 
We will do it by hashing the array to get first value. Then hashing again to find the position.
So that we can easily decrease it to O(1)

Another improvement can be made in finding the most consequtive sequence of word amounts from the position list. In average, this list is very small compared to 
actual text so it does not effect the complexity. Yet further optimizations may help improving the runtimes in final bruteforce algorithm and skiplist algorithm.

</pre>
  
