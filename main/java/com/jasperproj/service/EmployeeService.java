package com.jasperproj.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.jasperproj.entity.Employee;
import com.jasperproj.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	
	public Employee saveEmp(Employee emp) {
		return repository.save(emp);
	}
	
	public Employee update(Employee emp) {
		return repository.save(emp);
	}
	
	public List<Employee> get(){
		return repository.findAll();
	}
	
	public List<Employee> listPost(List<Employee> emp){
		return repository.saveAll(emp);
	}
	
	public String getreport(Object key,String format ) throws FileNotFoundException, JRException
	{
		String filepath= "C:\\E brain\\project\\jasperproj\\src\\main\\resources\\reports\\EmployeeProj.jrxml";
		String path="C:\\copies";
	List<Employee> employee = repository.getfilter(key);

	File file=ResourceUtils.getFile(filepath);
	JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
	JRBeanCollectionDataSource datasource=new JRBeanCollectionDataSource(employee);
	Map<String,Object> map=new HashMap<>();
	map.put("createdBy","emplydetails");
	JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,datasource);
	if(format.equalsIgnoreCase("html"))
	{
		JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\vk.html");
	}
	if(format.equalsIgnoreCase("pdf"))
	{
		JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\vk.pdf");
	}
	return "report generated in path:"+path;
	}


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException
			{
			
			String filepath="C:\\E brain\\project\\jasperproj\\src\\main\\resources\\reports\\EmployeeProj.jrxml";
				
			String path="C:\\copies";
			
			
			List<Employee> employee =  repository.findAll();

			File file =  ResourceUtils.getFile(filepath);
			
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(employee);
			
			Map<String,Object> map=new HashMap<>();
			
			map.put("createdBy","EmployeeDetails");
			
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,datasource);
			
			if(reportFormat.equalsIgnoreCase("html"))
				
			{
				JasperExportManager.exportReportToHtmlFile(jasperPrint,path+ "\\employees.html");
			}
			
			if(reportFormat.equalsIgnoreCase("pdf"))
				
			{
				JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\employees.pdf");
			}
			
			return "report generated in path:"+path;
			
			}
    
         public Collection<?> generateUserList() {
    	
    	 return repository.findAll();
    }
	
         public byte[] getBykey(Object key) throws FileNotFoundException, JRException {
     		
     		List<Employee> employee = repository.findBykey(key);
     		String filepath = "C:\\\\E brain\\\\project\\\\jasperproj\\\\src\\\\main\\\\resources\\\\reports\\\\EmployeeProj.jrxml";

     		// load file and compile it
     		File file = ResourceUtils.getFile(filepath);
     		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
     		// maping jasper report and find all
     		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employee);

     		Map<String, Object> map = new HashMap<String, Object>();
     		map.put("createdBy", "😎");

     		// print jasper report
     		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
     		return JasperExportManager.exportReportToPdf(jasperPrint);
     	}
	}

