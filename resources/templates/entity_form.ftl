<#import "utils.ftl" as u>
<#assign class_name_cap = class.name?cap_first>
<#assign class_name = class.name?uncap_first>
<#assign class_name_id = "${" + class_name + ".id" + "}">
<#assign class_name_plural = u.plural(class_name)>
<#assign opening_bracket = "${">
<#assign closing_bracket = "}">
<#macro print_complex_property prop>
    <#local property_name_url = prop.type?uncap_first />
    <#local property_name = prop.type />
    <#local property_name_cap = property_name?cap_first />
    <#local property_id = "${" + class_name + "." + property_name + ".id" + "}" />
    <td><a href="<c:url value="/${property_name_url}/${property_id}"/>">${property_name_cap} ${property_id}</a></td>
</#macro>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>New ${class_name_cap} form</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<c:url var="action" value="/${class_name}" />
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 border p-4">
            <h5 class="text-center">New ${class_name_cap} form</h5>
            <form:form class="p-2" action="/${class_name_plural}" method="post" modelAttribute="${class_name}">
                <#list properties as property>
                    <#assign label= "<form:label path=\"${property.type}\">${property.type?cap_first}</form:label>">
                    <#if entity_properties[property.type]??>
                        <#if property.upper == -1>
                        <#-- @ManyToMany  or @OneToMany -->
                            <div class="form-group " <#if property.persistentAnnotationName == "@OneToMany">style="display: none;"</#if>>
                                ${label}
                                <form:checkboxes items="${opening_bracket}${u.plural(property.name)}${closing_bracket}" path="${property.name}" element="div class='checkbox border rounded p-2' " itemValue="id"/>
                            </div>
                        <#elseif property.upper == 1>
                        <#-- @ManyToOne or @OneToOne -->
                            <div class="form-group">
                                ${label}
                                <form:select path="${property.type}" cssClass="form-control">
                                    <option value="-1">Select a ${property.type}</option>
                                    <form:options items="${opening_bracket}${u.plural(property.type)}${closing_bracket}"
                                                  itemValue="id"/>
                                </form:select>
                            </div>
                        </#if>
                    <#elseif enum_types?seq_contains(property.type)>
                        ${label}
                        <form:select path="${property.type}" cssClass="form-control">
                            <option value="">Select a ${property.type}</option>
                            <c:set var="enum_val">
                                <#list enum_values[property.type] as val>
                                    ${val}
                                    <#sep>,</#sep>
                                </#list>
                            </c:set>
                            <c:forEach items="${opening_bracket}enum_val${closing_bracket}" var="val">
                                <option value="${opening_bracket}val${closing_bracket}"
                                <c:if test="${opening_bracket} val == ${class_name}.${property.type}${closing_bracket}">
                                    selected
                                </c:if>
                                >${opening_bracket}val${closing_bracket}</option>
                            </c:forEach>
                        </form:select>
                    <#else>
                    <#-- simple data type -->
                    <#-- checkbox -->
                        <#if property.type == "Boolean" || property.type == "boolean">
                            <div class="form-group">
                                <form:checkbox path="${property.type}" />
                                ${label}
                            </div>
                        <#else>
                        <#-- text -->
                        <#-- textarea TODO -->
                            <div class="form-group">
                                ${label}
                                <form:input cssClass="form-control" path="${property.type}" />
                            </div>
                        </#if>
                    </#if>
                </#list>
                <div>
                    <button class="btn btn-success float-right" type="submit">Add ${class_name}</button>
                </div>
            </form:form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>