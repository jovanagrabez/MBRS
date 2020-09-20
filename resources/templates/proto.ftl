syntax = "proto3";
option java_multiple_files = true;
package ${package};

<#list classes as class>
message ${class.name}Message {
    <#list class.properties as property>
    ${grpcType[property.type]} ${property.name} = ${property?index + 1};
    </#list>
    <#list class.FMLinkedProperty as property>
    <#if property.upper == 1>
    int32 ${property.name} = ${class.properties?size + property?index + 1};
    </#if>
    </#list>
}

</#list>


message Id {
int32 id = 1;
}

message Void {}

<#list classes as class>
service ${class.name}Service {
    rpc getAll (Void) returns (stream ${class.name}Message);

    rpc save (${class.name}Message) returns (${class.name}Message);

    rpc getOne (Id) returns (${class.name}Message);

    rpc delete (Id) returns (Void);
}

</#list>