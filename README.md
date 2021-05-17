# Deciphering-Distant-Aliens-26

# First thoughts

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

However, our implementation is not returning back to index after finding consecutive ones. When we first implemented it, we did not realize the problem. The problem
here is because we are not going back to next index and we are continuing the index after consecutive words. To clarify the situation think the example at the top. 
In described implementation we are turning back to 6th index. But in our implementation we are continuing with index 20. This later implementation is not correct.
But its worst time complexity is not O(n^2) instead O(n).

There is an example case where first implementation works and second not.
Word: abccba
Text: abccbabccbaabccba
The first implementation returns abccb<b>abccbaabccba</b>
The second implementation returns <b>abccba</b>bccba<b>abccba</b>
</pre>
  
  
