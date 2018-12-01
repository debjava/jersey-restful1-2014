package au.com.example.persistence.dao.query;

import java.util.List;

public interface QueryString {
	public String getStatement();

	public List<QueryParameter> getParameters();
}
