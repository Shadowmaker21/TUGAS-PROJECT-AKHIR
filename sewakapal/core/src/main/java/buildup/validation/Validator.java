package buildup.validation;

public interface Validator<T> {
    boolean validate(T item);

    void setError(boolean show);
}
