package math;

public class Problems {
    public static int trailingZeroesInFactorial(int n) {
        int count = 0;
        
        for (int i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        
        return count;
    }
    
    int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
    
    // return x^y % z
    int expModulus(int x, int y, int p){
        int res = 1; // Initialize result
        
        while (y > 0) {
            
            // If y is odd, multiply x with result
            if ((y & 1) != 0) {
                res = res * x;
            }
            
            // y must be even now
            y = y >> 1; // y = y/2
            x = x * x; // Change x to x^2
        }
        return res % p;
    }
    
    boolean isPowerOfTwo(int n) {
        int count = 0;
        for (int i = 1; i <= 32; i++) {
            int x = 1 << i;

            if ((n & x) > 0) {
                ++count;
            }
        }

        return count == 1;
    }
}
