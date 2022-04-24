package tn.enicar.spring.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	public boolean addFile(MultipartFile file);
}
