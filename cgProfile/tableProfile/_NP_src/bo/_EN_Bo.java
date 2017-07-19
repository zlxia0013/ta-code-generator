package %%PACKAGE%%;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tc.ta.util.exception.ComRuntimeException;
import %%ENTITY_BASE_PACKAGE%%.bo.%%ENTITY_CLASS_NAME%%Bo;
import %%ENTITY_BASE_PACKAGE%%.dao.%%ENTITY_CLASS_NAME%%Dao;
import %%ENTITY_BASE_PACKAGE%%.dto.*;
import %%ENTITY_BASE_PACKAGE%%.pojo.%%ENTITY_CLASS_NAME%%;

@Service
public class %%ENTITY_CLASS_NAME%%Bo {
    @Autowired
    private %%ENTITY_CLASS_NAME%%Dao %%ENTITY_CLASS_NAME_VAR%%Dao;

    public %%ENTITY_CLASS_NAME%% getById(Integer id) {
		return %%ENTITY_CLASS_NAME_VAR%%Dao.getById(id);
    }

    public void add(%%ENTITY_CLASS_NAME%% %%ENTITY_CLASS_NAME_VAR%%) {
		validateAddUpdate(%%ENTITY_CLASS_NAME_VAR%%, true);
		%%ENTITY_CLASS_NAME_VAR%%Dao.add(%%ENTITY_CLASS_NAME_VAR%%);
    }

    public void update(%%ENTITY_CLASS_NAME%% %%ENTITY_CLASS_NAME_VAR%%) {
		validateAddUpdate(%%ENTITY_CLASS_NAME_VAR%%, false);
		%%ENTITY_CLASS_NAME_VAR%%Dao.update(%%ENTITY_CLASS_NAME_VAR%%);
    }

    private void validateAddUpdate(%%ENTITY_CLASS_NAME%% %%ENTITY_CLASS_NAME_VAR%%, boolean isAdd) {
		if (%%ENTITY_CLASS_NAME_VAR%% == null) {
		    throw new ComRuntimeException("对象不能为空");
        }
    }

    public void delete(Integer %%ENTITY_CLASS_NAME_VAR%%Id) {
		%%ENTITY_CLASS_NAME_VAR%%Dao.delete(%%ENTITY_CLASS_NAME_VAR%%Id);
    }

    public List<%%ENTITY_CLASS_NAME%%> searchPojo(%%ENTITY_CLASS_NAME%%SearchCondition sc) {
		return %%ENTITY_CLASS_NAME_VAR%%Dao.searchPojo(sc);
    }

    public List<%%ENTITY_CLASS_NAME%%Dto> searchDto(%%ENTITY_CLASS_NAME%%SearchCondition sc) {
		return %%ENTITY_CLASS_NAME_VAR%%Dao.searchDto(sc);
    }

    public Integer searchCnt(%%ENTITY_CLASS_NAME%%SearchCondition sc) {
		return %%ENTITY_CLASS_NAME_VAR%%Dao.searchCnt(sc);
    }

		//======================================= new method create here ====================================/
}