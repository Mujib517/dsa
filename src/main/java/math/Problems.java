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
    
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        
        int count = 0;
        for (int i = 1; i <= 32; i++) {
            if (((1 << i) & n) > 0) {
                ++count;
            }
        }
        
        return count == 1;
    }
    
    public int addDigits(int num) {
        
        while (num > 9) {
            
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            
            num = sum;
        }
        
        return num;
    }
    
    /*
    n=3^x we need to find x
    
    x = log(n) base 3
    
    x = logn/log3  (base 10)
    
    }
    */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        
        // %1 to check if it's an integer
        return Math.log10(n) / Math.log10(3) % 1 == 0;
    }
    
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        
        return (Math.log10(n) / Math.log10(4)) % 1 == 0;
    }
}
