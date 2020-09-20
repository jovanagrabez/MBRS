package myplugin.generator.fmmodel;


public class FMProperty extends FMElement {
    //Property type
    private String type;
    // Property visibility (public, private, protected, package)
    private String visibility;
    //Multiplicity (lower value)
    private Integer lower;
    //Multiplicity (upper value)
    private Integer upper;
    private String columnName;
    private Integer length;
    private IdentificationType generatedValue;
    private Boolean unique;
    private Integer precision;


    /**
     * @ToDo: Add length, precision, unique... whatever is needed for ejb class generation
     * Also, provide these meta-attributes or tags in the modeling languange metaclass or
     * stereotype
     */
    public FMProperty(String name, String type, String visibility, String columnName, Integer length, Boolean unique, Integer precision, int lower, int upper) {
        super(name);
        this.type = type;
        this.visibility = visibility;
        this.lower = lower;
        this.upper = upper;
        this.columnName = columnName;
        this.precision = precision;
        this.length = length;
        this.unique = unique;
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

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Integer getLower() {
        return lower;
    }

    public void setLower(Integer lower) {
        this.lower = lower;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }
}
