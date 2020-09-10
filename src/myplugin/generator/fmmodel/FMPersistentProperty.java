package myplugin.generator.fmmodel;

public class FMPersistentProperty extends FMProperty {

    private Boolean id;
    private String columnName;
    private Integer length;
    private IdentificationType generatedValue;
    private Boolean unique;


    public FMPersistentProperty(String name, String type, String visibility, String columnName, Integer length, Boolean unique, Integer precision, int lower, int upper) {
        super(name, type, visibility, columnName, length, unique, precision, lower, upper);
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public IdentificationType getGeneratedValue() {
        return generatedValue;
    }

    public void setGeneratedValue(IdentificationType generatedValue) {
        this.generatedValue = generatedValue;
    }

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }




}
