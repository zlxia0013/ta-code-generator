package %%PACKAGE%%;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import %%ENTITY_BASE_PACKAGE%%.dto.%%ENTITY_CLASS_NAME%%Dto;
import %%ENTITY_BASE_PACKAGE%%.dto.%%ENTITY_CLASS_NAME%%SearchCondition;
import %%ENTITY_BASE_PACKAGE%%.pojo.%%ENTITY_CLASS_NAME%%;

public interface %%ENTITY_CLASS_NAME%%Dao {
		%%ENTITY_CLASS_NAME%% getById(@Param("id") int id);

		void add(%%ENTITY_CLASS_NAME%% %%ENTITY_CLASS_NAME_VAR%%);

		void update(%%ENTITY_CLASS_NAME%% %%ENTITY_CLASS_NAME_VAR%%);

		void delete(Integer %%ENTITY_CLASS_NAME_VAR%%Id);

		List<%%ENTITY_CLASS_NAME%%> searchPojo(%%ENTITY_CLASS_NAME%%SearchCondition sc);

		List<%%ENTITY_CLASS_NAME%%Dto> searchDto(%%ENTITY_CLASS_NAME%%SearchCondition sc);

		Integer searchCnt(%%ENTITY_CLASS_NAME%%SearchCondition sc);

//======================================= new method create here ====================================/

}