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
<c:url var="action" value="/${u.plural(class_name?lower_case)}" />
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 border p-4">
            <h5 class="text-center">New ${class_name_cap} form</h5>
            <form:form class="p-2" action="/${class_name_plural?lower_case}" method="post" modelAttribute="${class_name?uncap_first}">
                <form:hidden path="id"/>
                <#list properties as property>
                    <#assign label= "<form:label path=\"${property.name}\">${property.name?cap_first}</form:label>">
                    <#if property.type == 'string' || property.type == 'String'>
                        <div class="form-group">
                            <label for="${property.name}">${property.name?cap_first}</label>
                            <form:input type="text" class="form-control" path="${property.name}" />
                        </div>
                    </#if>
                </#list>
                <#list class.FMLinkedProperty as property>
                <#if property.upper == 1>
                    <div class="form-group">
                        <label for="${u.plural(property.name?uncap_first)}">${u.plural(property.name?cap_first)}</label>
                        <form:select path="${property.name}.id">
                            <c:forEach var="item" items="${r"${"} ${u.plural(property.name?uncap_first)} ${"}"}">
                                <option value="${r"${"} item.id ${"}"}" ${r"${"} ${class_name?uncap_first}.${property.name}.id == item.id ? 'selected' : '' ${"}"}>${r"${"} item.displayName ${"}"}</option>
                            </c:forEach>
                        </form:select>
                    </div>
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