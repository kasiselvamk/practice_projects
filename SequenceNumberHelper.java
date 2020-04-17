import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public final class SequenceNumberHelper extends SequenceNumberDataProvider {

private static Map<SequenceNumber,SequenceNumberHolder> catchedSequenceNumber = new ConcurrentHashMap<SequenceNumber,SequenceNumberHolder>();
private static final SequenceNumberHelper  sequenceNumberHelper = new SequenceNumberHelper();
private final ReentrantLock lock = new ReentrantLock();

    //private constructor to avoid clients to call new on it
    private SequenceNumberHelper() {}

public static SequenceNumberHelper getInstance(){
    return sequenceNumberHelper;
    }

    public Long getNextSequenceNumberByModule(SequenceNumber module) {
        SequenceNumberHolder sequenceNumberHolder =  this.getSequenceNumberHolderByModule(module);
        return this.getSetIncrementedValue(sequenceNumberHolder,module);
}

    private Long getSetIncrementedValue(SequenceNumberHolder sequenceNumberHolder,SequenceNumber module) {
    synchronized (sequenceNumberHolder){
        if( sequenceNumberHolder.getSeqStart() > sequenceNumberHolder.getSeqEnd() ){
            return this.getSetIncrementedValue(loadCatchedSequenceNumberByModule(module),module);
        }
        Long currentSeqNumber = sequenceNumberHolder.getSeqStart();
        sequenceNumberHolder.setSeqStart(sequenceNumberHolder.getSeqStart()+1);
        return currentSeqNumber;
    }
    }

    private  SequenceNumberHolder getSequenceNumberHolderByModule(SequenceNumber module){
       SequenceNumberHolder sequenceNumberHolder = null;
        if(!catchedSequenceNumber.containsKey(module)){
            sequenceNumberHolder = this.loadCatchedSequenceNumberByModule(module);
        }else {
            sequenceNumberHolder = catchedSequenceNumber.get(module);
        }
        return catchedSequenceNumber.get(module);
                //catchedSequenceNumber.putIfAbsent(module,this.loadCatchedSequenceNumberByModule(module));
    }
    public void loadCatch (SequenceNumber module){
this.loadCatchedSequenceNumberByModule(module);
    }

    private SequenceNumberHolder loadCatchedSequenceNumberByModule(SequenceNumber module) {
        SequenceNumberHolder sequenceNumberHolder = new SequenceNumberHolder();
        System.out.println(module.name() + " " + Thread.currentThread().getName());
        sequenceNumberHolder.setSeqStart(1L);
        sequenceNumberHolder.setSeqEnd(100000000000L);
        catchedSequenceNumber.put(module,sequenceNumberHolder);
        return catchedSequenceNumber.get(module);
    }

}
