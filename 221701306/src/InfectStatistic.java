import java.util.List;
import java.util.Map;
import java.io.File;
import org.junit.Test;

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
    
    BaseArgument() {
        name="";
        value="";
    }
    
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
        if(strs.length>2) {
            value="*";
        }
        value+=strs[1];
    }
    
    boolean checkError() {
        //-outֻ��һ������ֵ
        if(value.startsWith("*")) {
            return false;
        }
        //������������ļ�·��û�к�׺ ���׺��Ϊ".txt" //���޸�ΪĬ��
        if(!value.endsWith(".txt")) {
            int x=value.lastIndexOf('.');
            if(x>=0)
            {
                value=value.substring(0, x);
            }
            value+=".txt";
            System.out.println("����ļ���ʽ���󣬸�Ϊ"+value);
        }
        return true;
    }
}

class DateArgument extends BaseArgument{

    public DateArgument() {
        // TODO Auto-generated constructor stub
        name="-date";
        value="";
    }
    DateArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        if(strs.length>2) {
            value="*";
        }
        value+=strs[1];
    }
    
    boolean checkError() {
        //-dateֻ��һ������ֵ
        if(value.startsWith("*")) {
            return false;
        }
        //���ڸ�ʽ��� ����ƽ�������YYYY-MM-DD��ʽ
        if(!value.matches("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]"
            + "|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468]"
            + "[048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)   \r\n(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]"
            + "{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))"
            + "|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))"
            + "-02-29) \r\n"))
        {
            value="";
            System.out.println("���ڸ�ʽ���󣬸�ΪĬ����������");
        }
        return true;
    }
}

class TypeArgument extends BaseArgument{

    String[] valueList;
    
    TypeArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        String[] valueList=new String[strs.length-1];
        for(int i=1;i<strs.length;++i) {
            valueList[i-1]=strs[i];
        }
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