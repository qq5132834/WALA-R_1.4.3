package com.ibm.wala.core.tests.zuk;

import java.io.File;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.io.FileProvider;

public class ZukAnalysisClass {
  
  private final static String path = "D:/development/github/WALA-R_1.4.3/eclipse/WALA-R_1.4.3/com.ibm.wala.core.tests/src/com/ibm/wala/core/tests/zuk/";
  private final static String jar = path + "zuk.jar";
  private final static String exclusions =  path + "zuk.txt";
  
  public static void classAnalysis() throws Exception {
    File exclusionsFile = new FileProvider().getFile(exclusions);
    AnalysisScope scope = AnalysisScopeReader.makeJavaBinaryAnalysisScope(jar, exclusionsFile);
    ClassHierarchy cha = ClassHierarchyFactory.make(scope);
    
    
    for(IClass klass : cha){
        if(klass.getName().toString().contains("zuk")){
          System.err.println(klass.getName());
        }
        else{
          System.out.println(klass.getName());
        }
        
        for (IMethod m : klass.getAllMethods()) {
          String mname = m.getName().toString();
          System.out.println("  method:" + mname);
        }
    }
    
    //给定一个表示类名的字符串，您可以先查找它，然后尝试应用程序加载程序，如下所示：
    IClass zukClass = cha.lookupClass(TypeReference.findOrCreate(ClassLoaderReference.Application, "Lcom/ibm/wala/core/tests/zuk/ZukAnalysisClass"));
    if(zukClass != null){
      System.out.println(zukClass.getName());
    }

  } 
  
  public static void main(String[] args) throws Exception {
    classAnalysis();
  }

}
