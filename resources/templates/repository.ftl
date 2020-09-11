package ${class.typePackage};

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import ejb.*;

import ejb.${class.name};


@Repository
public interface ${class.name}Repository extends JpaRepository<${class.name}, Long> {

}