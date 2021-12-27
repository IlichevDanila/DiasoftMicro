package diasoft.dilichev.tutorial.REST.objects;

public class Sequence {
    private int count;
    private int[] sequence;

    public Sequence() {
        count = 2;
        sequence = new int[2];
        sequence[0] = 1;
        sequence[1] = 1;
    }

    private Sequence(int size, Sequence source)
    {
        if(size > source.count)
        {
            size = source.count;
        }
        count = size;

        sequence = new int[count];
        for(int i = 0; i < count; i++)
        {
            sequence[i] = source.sequence[i];
        }
    }

    public int[] getSequence()
    {
        return sequence;
    }

    public void add()
    {
        count++;
        int[] newSeq = new int[count];
        for(int i = 0; i < count - 1; i++)
        {
            newSeq[i] = sequence[i];
        }
        newSeq[count - 1] = newSeq[count - 2] + newSeq[count - 3];
        sequence = newSeq;
    }

    public void delete(int idx)
    {
        if(idx < 2) {
            idx = 2;
        }

        count = idx;
        int[] newSeq = new int[count];
        for(int i = 0; i < count - 1; i++)
        {
            newSeq[i] = sequence[i];
        }
        sequence = newSeq;
    }

    public void change(int idx, int val)
    {
        if(idx < 0) {
            return;
        }

        sequence[idx] = val;

        for(int i = idx + 1; i < count; i++)
        {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }
    }

    public Sequence getSubsequence(int size)
    {
        return new Sequence(size, this);
    }

    public int getCount() {
        return count;
    }
}
