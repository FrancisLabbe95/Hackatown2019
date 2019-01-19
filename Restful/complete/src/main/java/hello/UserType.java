package hello;

/**
 * Created by Fred on 2019-01-19.
 */
public enum UserType {
    PRODUCTEUR(0),
    RAMASSEUR(1);

    private int _value;

    UserType(int Value) {
        this._value = Value;
    }

    public int getValue() {
        return _value;
    }

    public static UserType fromInt(int i) {
        for (UserType b : UserType .values()) {
            if (b.getValue() == i) { return b; }
        }
        return null;
    }
}
