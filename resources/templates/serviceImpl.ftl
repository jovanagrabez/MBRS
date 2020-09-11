package ${class.typePackage};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ftn.mbrs.repository.${class.name}Repository;
import uns.ftn.mbrs.service.${class.name}Service;
import uns.ftn.mbrs.model.*;

import java.util.Date;


@Service
public class ${class.name}ServiceImpl implements ${class.name}Service {

@Autowired
private ${class.name}Repository ${class.name?uncap_first}Repository;

@Override
public List < ${class.name} > getAll() {
return this.${class.name?uncap_first}Repository.findAll();
}

@Override
public Optional <${class.name}> getOne(Long id) {
return this.${class.name?uncap_first}Repository.findById(id);
}

@Override
public void update(${class.name} ${class.name?uncap_first}) {
this.${class.name?uncap_first}Repository.save(${class.name?uncap_first});
}

@Override
public void addTodo(${class.name} ${class.name?uncap_first}) {
this.${class.name?uncap_first}Repository.save();
}

@Override
public void delete(Long id) {
Optional <${class.name}> ${class.name?uncap_first} = ${class.name?uncap_first}Repository.findById(id);
if (${class.name?uncap_first}.isPresent()) {
${class.name?uncap_first}Repository.delete(${class.name?uncap_first}.get());
}
}


