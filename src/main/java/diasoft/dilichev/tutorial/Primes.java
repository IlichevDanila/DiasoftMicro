package diasoft.dilichev.tutorial;

public class Primes {
    private final long id;
    private final int count;
    private int[] primes;

    public Primes(long id, int count) {
        this.id = id;
        this.count = count;

        primes = (count > 0) ? new int[count] : null;

        int n = 0;
        int current = 2;
        boolean good;
        while(n < count)
        {
            good = true;
            for(int i = 2; i * i <= current; ++i)
            {
                if(current % i == 0)
                {
                    good = false;
                    break;
                }
            }
            if(good)
            {
                primes[n] = current;
                ++n;
            }
            ++current;
        }
    }

    public long getId() {
        return id;
    }

    public int[] getPrimes() {
        return primes;
    }
}
