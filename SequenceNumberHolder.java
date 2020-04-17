public class SequenceNumberHolder {
    private Long seqStart;
    private Long seqEnd;

    public SequenceNumberHolder() {
        this.seqStart = 0L;
        this.seqEnd = 0L;
    }

    public Long getSeqEnd() {
        return seqEnd;
    }

    public void setSeqEnd(Long seqEnd) {
        this.seqEnd = seqEnd;
    }

    public Long getSeqStart() {
        return seqStart;
    }

    public void setSeqStart(Long seqStart) {
        this.seqStart = seqStart;
    }
    
}
