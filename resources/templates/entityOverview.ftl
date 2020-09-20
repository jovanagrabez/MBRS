<#import "utils.ftl" as u>
<#assign class_name_cap = class.name?cap_first>
<#assign class_name = class.name?uncap_first>
<#assign class_name_id = "${" + class_name + ".id" + "}">
<#assign class_name_plural = u.plural(class_name)>
<#assign empty_word = "empty ">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>List of ${class_name_plural}</title>
</head>
<body>
<%@ include file="navbar.jsp"%>
<script>
    $(document).ready( function () {
        var table = $('#table_id').DataTable(
            {
                responsive: true
            }
        );
    } );
</script>
<div class="container mt-3">
    <h5 class="text-center">List of ${class_name_plural}</h5>
    <div>
        <a class="btn btn-primary btn-sm float-right mb-3" href="<c:url value="/${u.plural
        (class_name?lower_case)}/new/"/>">Create new ${class_name?cap_first}</a>
    </div>
    <div>
        <table id="table_id" class="table table-sm table-hover table-bordered text-center mt-3">
            <thead class="bg-light">
            <tr>
                <#list properties as property>
                    <th>${property.name?cap_first}</th>
                </#list>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ "${" + class_name_plural + "}" }" var="${class_name}">
                <tr>
                    <#list properties as property>
                        <#if entity_properties[property.type]??>
                            <#-- Not sure about @ManyToMany -->
                                <td>
                                    <c:if test="${ "${" + empty_word  + class_name + "." + property.name + "}" }">[...]</c:if>
                                    <c:forEach items="${ "${" + class_name + "." + property.name + "}" }" var="${property.name}_single">
                                        <#assign property_id = "${" + property.name + "_single" + "}" />
                                        ${property_id}
                                    </c:forEach>
                                </td>
                        <#else>
                            <td>${ "${" + class_name + "." + property.name +  "}" }</td>
                        </#if>
                    </#list>
                    <td>
                        <a class="btn btn-sm btn-info" href="<c:url value="/${u.plural(class_name?lower_case)}/${class_name_id}"/>">Detail</a>
                        <a class="btn btn-sm btn-primary" href="<c:url value="/${u.plural(class_name?lower_case)}/${class_name_id}/edit"/>">Edit</a>
                        <a class="btn btn-sm btn-danger" href="<c:url value="/${u.plural(class_name?lower_case)}/${class_name_id}/delete"/>">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>