package ${class.typePackage}.services;

import java.util.Optional;
import java.util.List;
import java.util.Set;
import uns.ftn.mbrs.model.*;
import org.springframework.stereotype.Service;

@Service
public interface ${class.name}Service{


List <${class.name}> getAll();

Optional < ${class.name} > getOne(Long id);

void update(${class.name} ${class.name?lower_case});

void add(${class.name} ${class.name?lower_case});

void delete(Long id);

