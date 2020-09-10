package myplugin.generator.fmmodel;

public class FMLinkedProperty extends FMProperty{
    private CascadeType cascade;
    private FetchType fetch;
    private String mappedBy;
    private Boolean optional;
    private  Integer oppositeEnd;


    public FMLinkedProperty(String name, String type, String visibility, String columnName, Integer length, Boolean unique, Integer precision, int lower, int upper) {
        super(name, type, visibility, columnName, length, unique, precision, lower, upper);
    }

    public FMLinkedProperty(String name, String type, String visibility, String columnName, Integer length, Boolean unique, Integer precision, int lower, int upper, CascadeType cascade, FetchType fetch, String mappedBy, Boolean optional, Integer oppositeEnd) {
        super(name, type, visibility, columnName, length, unique, precision, lower, upper);
        this.cascade = cascade;
        this.fetch = fetch;
        this.mappedBy = mappedBy;
        this.optional = optional;
        this.oppositeEnd  = oppositeEnd;
    }

    public Integer getOppositeEnd() {
        return oppositeEnd;
    }

    public void setOppositeEnd(Integer oppositeEnd) {
        this.oppositeEnd = oppositeEnd;
    }

    public CascadeType getCascade() {
        return cascade;
    }

    public void setCascade(CascadeType cascade) {
        this.cascade = cascade;
    }

    public FetchType getFetch() {
        return fetch;
    }

    public void setFetch(FetchType fetch) {
        this.fetch = fetch;
    }

    public String getMappedBy() {
        return mappedBy;
    }

    public void setMappedBy(String mappedBy) {
        this.mappedBy = mappedBy;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }


}
