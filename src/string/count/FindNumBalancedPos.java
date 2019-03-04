package string.count;

/**
 * Find numbers of balancing positions in string
 * https://www.geeksforgeeks.org/find-numbers-balancing-positions-string/
 *
 * Given a string, the task is to find the number of such balancing positions
 * in the string from where the left and the right part of that string contains
 * same characters. The frequency of characters doesn’t matters.
 *
 * Input : str[] = abaaba
 * Output : Number of balancing positions : 3
 * Explanations : All 3 balancing positions are as :
 * ab|aaba, aba|aba, abaa|ba
 *
 * Input : str[] = noon
 * Output : Number of balancing positions : 1
 * Explanations : Balancing position is :
 * no|on
 *
 */

public class FindNumBalancedPos {
    private static final int MAX_CHAR = 256;

    public int findNumBalancedPos(String s){
        if (s == null || s.length() == 0){
            return 0;
        }

        char[] input = s.toCharArray();
        int res = 0;
        int[] left = new int[MAX_CHAR];
        int[] right = new int[MAX_CHAR];

        int rt = 0;  // type of character occurred
        int lt = 0;
        //fill the left map
        for (char c : input){
            if (right[c] == 0){
                rt++;
            }
            right[c]++;
        }

        for (char c : input){
            left[c] ++;
            right[c] --;

            if (right[c] == 0){
                break;
            }

            if (left[c] == 1){
                lt ++;
            }

            if (rt == lt){
                res ++;
            }
        }
        return res;
    }

    //自我感觉这个方法不好
    public int countBalance(String s) {
        char[] str=s.toCharArray();
        int n = str.length; // string length


        // hash array for storing hash of string
        // intialized by 0 being global
        int[] rightVisited = new int[MAX_CHAR];
        int[] leftVisited = new int[MAX_CHAR];

        // process string initially for rightVisited
        for (int i=0; i<n; i++)
            rightVisited[str[i]]++;

        // check for balancing points
        int res = 0;
        for (int i=0; i<n; i++)
        {
            // for every position inc left hash
            // & dec rightVisited
            leftVisited[str[i]]++;
            rightVisited[str[i]]--;

            // check whether both hash have same
            // character or not
            int j;
            for (j=0; j<MAX_CHAR; j++)
            {
                // Either both leftVisited[j] and
                // rightVisited[j] should have none
                // zero value or both should have
                // zero value
                if ( (leftVisited[j] == 0 &&
                        rightVisited[j] != 0) ||
                        (leftVisited[j] != 0 &&
                                rightVisited[j] == 0)
                )
                    break;
            }

            // if both have same character increment
            // count
            if (j == MAX_CHAR)
                res++;
        }
        return res;
    }

    public static void main(String[] args){
        FindNumBalancedPos findNumBalancedPos = new FindNumBalancedPos();
        int res1 = findNumBalancedPos.findNumBalancedPos("drerq");
        int res2 = findNumBalancedPos.countBalance("drerq");

        System.out.println("res1 : " + res1);
        System.out.println("res2 : " + res2);

    }
}
