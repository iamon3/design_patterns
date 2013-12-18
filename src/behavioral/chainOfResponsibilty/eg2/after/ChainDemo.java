package behavioral.chainOfResponsibilty.eg2.after;

/**
 * The client submits each request to the "chain" abstraction 
 * and is decoupled from all subsequent processing. 
 */
class Handler
{
    private static java.util.Random s_rn = new java.util.Random();
    
    // At the beginning total number of handlers will be 1. 
    // After an insertion of a handler, this count is incremented.
    private static int totalHandlers = 1;
    
    // This handler's Id.
    private int handlerId = totalHandlers++;
    
    // Reference to the next handler
    private Handler nextHandler;

    /**
     * Adds the next node i.e. handler at the end of the chain recursively
     */
    public void add(Handler next)
    {
        if (nextHandler == null)
          nextHandler = next;
        else
          nextHandler.add(next);
    }
    
    /**
     * This function is required to assign the root handler of handlers chain.
     * TODO Looks like there is an error in the logic. Rectify it.
     */
    public void wrap_around(Handler root)
    {
        if (nextHandler == null)
          nextHandler = root;
        else
          nextHandler.wrap_around(root);
    }
    
    /**
     * Handle the request, do the requisite processing and / or forward the request to the next handler
     * in the chain.
     */
    public void handle(int num)
    {
        if (s_rn.nextInt(4) != 0)
        {
            System.out.print(handlerId + "-busy  ");
            nextHandler.handle(num);
        }
        else
          System.out.println(handlerId + "-handled-" + num);
    }
}

public class ChainDemo
{
    public static void main(String[] args)
    {
        Handler chain_root = new Handler();
        chain_root.add(new Handler());
        chain_root.add(new Handler());
        chain_root.add(new Handler());
        chain_root.wrap_around(chain_root);
        for (int i = 1; i < 10; i++)
          chain_root.handle(i);
    }
}

/**
 * Expected Output
 * 1-busy  2-busy  3-handled-1
1-busy  2-busy  3-busy  4-busy  1-handled-2
1-busy  2-busy  3-busy  4-busy  1-busy  2-busy
    3-busy  4-busy  1-handled-3
1-busy  2-handled-4
1-busy  2-busy  3-busy  4-handled-5
1-busy  2-busy  3-busy  4-busy  1-busy  2-handled-6
1-busy  2-handled-7
1-handled-8
1-busy  2-busy  3-handled-9
 */