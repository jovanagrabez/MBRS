package ${class.typePackage};

import java.util.Optional;
import java.util.List;
import java.util.Set;
import org.lognet.springboot.grpc.GRpcService;
import uns.ftn.mbrs.*;
import uns.ftn.mbrs.Void;
import uns.ftn.mbrs.model.*;
import uns.ftn.mbrs.service.*;
import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

@GRpcService
@RequiredArgsConstructor
public class ${class.name}GrpcService extends ${class.name}ServiceGrpc.${class.name}ServiceImplBase {
    private final ${class.name}Service ${class.name?lower_case}Service;

    @Override
    public void getAll(Void request, StreamObserver<${class.name}Message> responseObserver) {
        ${class.name?lower_case}Service.getAll().stream()
        .map(this::convert)
        .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(${class.name}Message request, StreamObserver<${class.name}Message> responseObserver) {
        ${class.name?lower_case}Service.update(convert(request));
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(Id request, StreamObserver<${class.name}Message> responseObserver) {
        ${class.name?lower_case}Service.getOne(request.getId()).ifPresent(entity -> responseObserver.onNext(convert(entity)));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id request, StreamObserver<Void> responseObserver) {
        ${class.name?lower_case}Service.delete(request.getId());
        responseObserver.onCompleted();
    }

    private ${class.name}Message convert(${class.name} entity) {
        return ${class.name}Message.newBuilder()
        <#list class.properties as property>
        .set${property.name?capitalize}(entity.get${property.name?capitalize}())
        </#list>
        <#list class.FMLinkedProperty as property>
        <#if property.upper == 1>
        .set${property.name?capitalize}(entity.get${property.name?capitalize}().getId())
        </#if>
        </#list>
        .build();
    }

    private ${class.name} convert(${class.name}Message model) {
        ${class.name} entity = new ${class.name}();
        <#list class.properties as property>
        entity.set${property.name?capitalize}(model.get${property.name?capitalize}());
        </#list>
        <#list class.FMLinkedProperty as property>
        <#if property.upper == 1>
        ${property.type} ${property.name} = new ${property.type}();
        ${property.name}.setId(model.get${property.name?capitalize}());
        entity.set${property.name?capitalize}(${property.name});
        </#if>
        </#list>

        return entity;
    }
}
