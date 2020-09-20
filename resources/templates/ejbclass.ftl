package ${class.typePackage};
import javax.persistence.*;
import java.util.*;

<#list imports as import>
import ${import};
</#list>

@Entity
@Table
${class.visibility} class ${class.name} {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

<#list properties as property>
<#--	<#if property.upper == 1 -->
    @Column<#if (property.columnName)?? || (property.precision)?? || (property.length)??  || (property.unique)?? || property.lower == 0>(<#rt>
    <#if (property.columnName)??>
        <#lt>name = "${property.columnName}"<#rt>
    </#if>
    <#if (property.length)??>
        <#lt><#if (property.columnName)??>, </#if>length = ${property.length}<#rt>
    </#if>
    <#if (property.precision)??>
        <#lt><#if (property.columnName)?? || (property.length)??>, </#if>precision = ${property.precision}<#rt>
    </#if>
    <#if (property.unique)??>
        <#lt><#if (property.columnName)?? || (property.length)?? || (property.precision)??>, </#if>unique = ${property.unique?c}<#rt>
    </#if>
    <#if property.lower == 0>
        <#lt><#if (property.columnName)?? || (property.length)?? || (property.precision)??  || (property.unique)??>, </#if>nullable = true<#rt>
    </#if>
    <#lt>)</#if>
    ${property.visibility} ${property.type} ${property.name};

</#list>
<#--LINKED PROPERTIES GENERATING-->
<#list  class.FMLinkedProperty as property>
    <#if property.upper == -1 && property.oppositeEnd == -1>@ManyToMany<#elseif property.upper == -1 && property.oppositeEnd == 1>@OneToMany<#elseif property.upper == 1 && property.oppositeEnd== -1>@ManyToOne<#else>@OneToOne</#if><#rt>
    <#lt><#if (property.fetch)?? || (property.cascade)?? || (property.mappedBy)?? || (property.optional)?? >(<#rt>
    <#if (property.cascade)??>
        <#lt>cascade = CascadeType.${property.cascade}<#rt>
    </#if>
    <#if (property.fetch)??>
        <#lt><#if (property.cascade)??>, </#if>fetch = FetchType.${property.fetch}<#rt>
    </#if>
    <#if (property.mappedBy)??>
        <#lt><#if (property.cascade)?? || (property.fetch)??>, </#if>mappedBy = "${property.mappedBy}"<#rt>
    </#if>
    <#if (property.optional)??>
        <#lt><#if (property.cascade)?? || (property.fetch)?? || (property.mappedBy)??>, </#if>optional = ${property.optional?c}<#rt>
    </#if>
    <#lt>)</#if>
    ${property.visibility} <#rt>
    <#if property.upper == -1>
        <#lt>Set<<#rt>
    </#if>
    <#lt>${property.type?cap_first}<#rt>
    <#if property.upper == -1>
        <#lt>><#rt>
    </#if>
    <#lt> ${property.name};<#if !property?is_last>${'\n'}</#if>
</#list>

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }


<#list properties as property>
    <#if property.upper == 1 >
    public ${property.type} get${property.name?cap_first}(){
        return ${property.name};
    }

    public void set${property.name?cap_first}(${property.type} ${property.name}){
        this.${property.name} = ${property.name};
    }

    <#elseif property.upper == -1 >
    public Set<${property.type}> get${property.name?cap_first}(){
        return ${property.name};
    }

    public void set${property.name?cap_first}( Set<${property.type}> ${property.name}){
        this.${property.name} = ${property.name};
    }

    <#else>
        <#list 1..property.upper as i>
    public ${property.type} get${property.name?cap_first}${i}(){
        return ${property.name}${i};
    }

    public void set${property.name?cap_first}${i}(${property.type} ${property.name}${i}){
        this.${property.name}${i} = ${property.name}${i};
    }

        </#list>
    </#if>
</#list>
<#--LINKED PROPERTY GETTERS AND SETTERS-->
<#list class.FMLinkedProperty as property>
    public <#rt>
    <#if property.upper == -1>
        <#lt>Set<<#rt>
    </#if>
    <#lt>${property.type?cap_first}<#rt>
    <#if property.upper == -1>
        <#lt>><#rt>
    </#if>
    <#lt> get${property.name?cap_first}() {
        return ${property.name};
    }

    public void set${property.name?cap_first}(<#rt>
    <#if property.upper == -1>
        <#lt>Set<<#rt>
    </#if>
    <#lt>${property.type}<#rt>
    <#if property.upper == -1>
        <#lt>><#rt>
    </#if>
    <#lt> ${property.name}) {
        this.${property.name} = ${property.name};
    }
</#list>

}
