import java.util.List;
import java.util.Map;

import java.io.File;

/**
 * InfectStatistic
 * TODO
 *
 * @author xxx
 * @version xxx
 * @since xxx
 */
public class InfectStatistic {
    public static void main(String[] args) {
        System.out.println("helloworld");
    }
}

class ListCommand{
    String name;
    BaseArgument[] argument;
    
    ListCommand(String[] strs) {
        // TODO Auto-generated constructor stub
    }
    
    void checkInput() {
        
    }
    
    void checkCommand() {
         
    }
    
}

abstract class BaseArgument{
    String name;
    String value;
    
    BaseArgument(String[] strs) {
        name=strs[0];
        value="";
    }
    
    abstract boolean checkError();
}

class LogArgument extends BaseArgument{

    LogArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        if(strs.length>2) {
            value="*";
        }
        value+=strs[1];
    }
    
    boolean checkError() {
        //-logֻ��һ������ֵ
        if(value.startsWith("*")) {
            return false;
        }
        //-log�Ĳ���ֵ��һ��Ŀ¼��·��
        File alog=new File(value);
        if(!alog.isDirectory()) {
            return false;
        }
        return true;
    }
}

class OutArgument extends BaseArgument{

    OutArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
    }
    
    boolean checkError() {
        return true;
    }
}

class DateArgument extends BaseArgument{

    DateArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
    }
    
    boolean checkError() {
        return true;
    }
}

class TypeArgument extends BaseArgument{

    String[] valuelist;
    
    TypeArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
    }
    
    boolean checkError() {
        return true;
    }
}

class ProvinceArgument extends BaseArgument{

    String[] valuelist;
    
    ProvinceArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
    }
    
    boolean checkError() {
        return true;
    }
}

class LogFiles{
    String lastDate;
    List<File> logFileList;
    
    LogFiles(String str){
        
    }
    
    void readFiles(String str){
        
    }
    
    //ͳ�Ƶ�i���ļ�������
    void statisFile(int i) {
        
    }
    
    //������־�ļ��е�һ�� ��statisFile()����
    static void statisLine(String s) {
        
    }
}

class Statistic{
    Map<String, int[]> data;
    
    public Statistic() {
        // TODO Auto-generated constructor stub
    }
    
    void outPutFile(String str) {
        
    }
}