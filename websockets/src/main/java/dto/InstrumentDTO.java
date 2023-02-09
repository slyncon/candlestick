package dto;

public class InstrumentDTO {

    private InstrumentEventDTO data;
    private Type type;
    public static enum Type {
        ADD,
        DELETE;
    }

    public InstrumentEventDTO getData() {
        return data;
    }

    public void setData(InstrumentEventDTO data) {
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
