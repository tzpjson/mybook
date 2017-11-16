package cn.tzp.bookstore.book.web.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;
import cn.tzp.bookstore.book.domain.Book;
import cn.tzp.bookstore.book.service.BookService;
import cn.tzp.bookstore.category.domain.Category;
import cn.tzp.bookstore.category.service.CategoryService;

public class AdminAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理编码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		//上传三步
		//创建工厂
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(15*1024,new File("D:/temp"));
		//创建解析器
		ServletFileUpload sfu = new ServletFileUpload(fileItemFactory);
		
		//设置图片大小
		sfu.setFileSizeMax(15*1024);
		
		try {
			//解析request对象，得到List<FileItem>
			List<FileItem> fileItemList = sfu.parseRequest(request);
			
			Map<String,String> map = new HashMap<String,String>();
			for(FileItem fileItem : fileItemList) {
				map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
			}
			
			Book book = CommonUtils.toBean(map, Book.class);
			book.setBid(CommonUtils.uuid());
			
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCatefory(category);
			
			//保存上传的文件
			//获取图片保存位置
			String savepath = this.getServletContext().getRealPath("/img");
			
			//获取文件名
			String filename = CommonUtils.uuid() + "_" +fileItemList.get(1).getName();
			
			//创建文件对象
			File destFile = new File(savepath,filename);
			
			//校验文件的扩展名
			if(!filename.toLowerCase().endsWith("jpg")) {
				request.setAttribute("msg", "你的图片不是jpg格式");
				request.setAttribute("categoryList", categoryService.findAll());
				
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			//保存文件
			fileItemList.get(1).write(destFile);
			
			book.setImage("/img/" + filename);
			
/*			//校验图片尺寸
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getWidth(null) > 200 || image.getHeight(null) > 200) {
				destFile.delete();
				request.setAttribute("msg", "你的图片不是jpg格式");
				
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}*/
			
			/**
			 * 使用service完成保存
			 */
			bookService.add(book);
			
			//转发
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);
			
		} catch (Exception e) {
			if( e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "你的图片超出15KB了");

				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
		
	}
}







