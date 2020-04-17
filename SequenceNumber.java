public enum SequenceNumber {
    STARTUP ("STARTUP"),
    EXP ("EXP"),
    HED ("HED");

    private final String module;

    SequenceNumber(String module) {
        this.module = module;
        SequenceNumberHelper.getInstance().loadCatch(this);
    }

    public Long Next(){
        return SequenceNumberHelper.getInstance().getNextSequenceNumberByModule(this);
    }

}
