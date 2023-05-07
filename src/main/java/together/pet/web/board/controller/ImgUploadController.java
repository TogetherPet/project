package together.pet.web.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

/**
 * @author 유선우
 * 파일 저장을 담당하는 컨트롤러입니다
 */
@RestController
public class ImgUploadController {
	
	/**
	 * @param multipartFile
	 * @return
	 * file 객체로 전달되는 데이터를 저장합니다
	 */
	@PostMapping("/uploadImg")
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		JsonObject jsonObject = new JsonObject();
		/**
		 * fileRoot : 저장될 외부 파일 경로
		 * originalFileName : 오리지날 파일명
		 * extension : 파일 확장자
		 */
		String fileRoot = "C:\\summernote_image\\";	
		String originalFileName = multipartFile.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		/**
		 * savedFileName : 저장될 파일 명
		 * targetFile : 파일경로 + 파일 이름을 지정합니다
		 */
		String savedFileName = UUID.randomUUID() + extension;	
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			/**
			 * fileStream : 파일 정보를 가져옵니다
			 * FileUtils.copyInputStreamToFile(파일정보, 파일경로+파일이름);
			 */
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			
			/**
			 * url : 파일을 저장한 경로를 입력합니다
			 * responseCode : 실행 결과를 success or error로 지정합니다
			 */
			jsonObject.addProperty("url", "http://localhost/summernoteImage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch (IOException e) {
			/**
			 * 예외 발생시 해당 파일을 삭제합니다
			 * FileUtils.deleteQuietly(삭제할 파일)
			 */
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		/**
		 * 해당 파일 정보를 스크립트에 전달합니다
		 */
		return jsonObject;
	}
}
