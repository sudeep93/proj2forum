package proj2.backend.implementation;

import proj2.backend.model.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);
	UploadFile getFile(String username);
}