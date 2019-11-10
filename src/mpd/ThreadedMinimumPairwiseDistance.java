package mpd;


public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
    int globalResult = Integer.MAX_VALUE;
    @Override
    public long minimumPairwiseDistance(int[] values) {
      //  throw new UnsupportedOperationException();
         this.values = values;

        Thread threadLowL = new Thread(new Lower_left());
        threadLowL.start();
        Thread threadTopR = new Thread(new Top_right());
        threadTopR.start();
        Thread threadBotR = new Thread(new Bottom_right());
        threadBotR.start();
        Thread threadC = new Thread(new center());
        threadC.start();

        try {
            // joining the threads
            threadLowL.join();
            threadTopR.join();
            threadBotR.join();
            threadC.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return globalResult;
    }

    public synchronized int changeGlobalResult(int result){
        if(globalResult > result){
            globalResult = result;
        }
        return globalResult;
    }

    }

