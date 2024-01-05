package com.jasperproj.controller;


import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasperproj.entity.Employee;
import com.jasperproj.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	
	@PostMapping("/post")
	public Employee saveEmp(@RequestBody Employee emp) {
		
		return service.saveEmp(emp);
	}
	
	
	@PutMapping("/update")
	public Employee update(@RequestBody Employee emp) {
		return service.update(emp);
	}
//	@GetMapping("/getAll/{format}")
//	public String exportReport(@PathVariable String format) throws FileNotFoundException, JRException{
//		return service.exportReport( format);
//		}
	
	@PostMapping("/listOfPost")
	public List<Employee> listOfPost(@RequestBody List<Employee> emp){
		return service.listPost(emp);
	}
	//postman filler
	@GetMapping("/generatereport/{format}")

	public String generatedReport(@PathVariable String format) throws FileNotFoundException, JRException
	{
		 service.exportReport(format);
		return  "YOUR REPORT GENERATED"
				+ "";
	}
	
	//chrome filler
	
	@GetMapping("/getreport/{key}/{format}")

	public Object getreport( @PathVariable("key")Object key,@PathVariable("format") String format) throws FileNotFoundException, JRException
	{
		service.getreport(key,format);
		return "report generated";
	}
	
	
	//postman filler
	
	@GetMapping("/getBykey/{key}")
	public ResponseEntity<byte [] >getBykey(@PathVariable ("key") Object key) throws FileNotFoundException, JRException {
		byte [] reports = service.getBykey(key);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		return new ResponseEntity<>(reports,header,HttpStatus.OK);
	}
	
	//postman get all
	
	@GetMapping("/generatepdf")
	public ResponseEntity<byte[]> generatepdf() throws FileNotFoundException,JRException
	{
	JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(service.generateUserList());
	JasperReport compileReport=JasperCompileManager.compileReport("C:\\E brain\\project\\jasperproj\\src\\main\\resources\\reports\\EmployeeProj.jrxml");
	HashMap<String,Object> map1=new HashMap<>();
	JasperPrint report=JasperFillManager.fillReport(compileReport,map1,beanCollectionDataSource);
	byte[] data=JasperExportManager.exportReportToPdf(report);
	HttpHeaders headers=new HttpHeaders();
	headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
	headers.add("Content-Disposition", "inline; filename=generated.pdf");

	return ResponseEntity.ok()
	        .headers(headers)
	        .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
            .body(data);
	}
	
}
