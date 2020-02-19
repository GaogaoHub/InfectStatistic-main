import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;
import org.junit.Test;

import java.lang.String;

/**
 * InfectStatistic TODO
 *
 * @author xxx
 * @version xxx
 * @since xxx
 */
public class InfectStatistic {
    public static void main(String[] args) {
        ListCommand aListCommand = new ListCommand(args);
        Statistic aStatistic = new Statistic();
        aListCommand.checkCommand(aStatistic);
        LogFiles aLogFiles = new LogFiles(aListCommand.arguments[0].value);
        aLogFiles.readFiles(aListCommand.arguments[2].value, aStatistic);
        aStatistic.outPutFile(aListCommand.arguments[1].value);
        System.out.println("helloworld");
    }

}

class ListCommand {
    String name;
    BaseArgument[] arguments;

    ListCommand(String[] strs) {
        // TODO Auto-generated constructor stub
        name = strs[0];
        arguments = new BaseArgument[5];
        arguments[0] = new LogArgument("-log");
        arguments[1] = new OutArgument("-out");
        arguments[2] = new DateArgument("-date");
        arguments[3] = new TypeArgument("-type");
        arguments[4] = new ProvinceArgument("-province");

        // ���������ʽ���
        if (!checkInput(strs)) {
            System.out.println("���������ʽ�����˳�����");
            System.exit(1);
        }

        // ���������� ��ֵarguments
        for (int i = 1, k = 0; i < strs.length && k < 5; ++i) {
            int n = 1;
            // �����strs[1]һ���ǲ���
            ++i;
            while (!strs[i].startsWith("-")) {
                ++n;
                ++i;
            }
            --i;
            // ������ֵBaseArgument��string����
            String[] argStrings = new String[n];
            for (int j = 0; j < n; ++j) {
                argStrings[j] = strs[i - n + 1 + j];
            }

            // ��ֵBaseArgument[]
            switch (argStrings[0]) {
            case "-log":
                arguments[0] = new LogArgument(argStrings);
                break;
            case "-out":
                arguments[1] = new OutArgument(argStrings);
                break;
            case "-date":
                arguments[2] = new DateArgument(argStrings);
                break;
            case "-type":
                arguments[3] = new TypeArgument(argStrings);
                break;
            case "-province":
                arguments[4] = new ProvinceArgument(argStrings);
                break;
            default:
                ;
            }
        }
    }

    boolean checkInput(String[] strs) {
        // �������list �򷵻�false
        if (!name.equals("list")) {
            return false;
        }
        // list���������
        if (!strs[1].startsWith("-")) {
            return false;
        }
        // ��������ȷ������������������ �򷵻�false
        for (int i = 1; i < strs.length; ++i) {
            if (strs[i].startsWith("-")) {
                if (!(strs[i].equals("-log") || strs[i].equals("-out") || strs[i].equals("-type")
                        || strs[i].equals("-date") || strs[i].equals("-province"))) {
                    return false;
                }
            }
        }
        // ������������-log��-out �򷵻ش��� //��-logֻ����һ��
        for (int i = 1; i < strs.length; ++i) {
            if (strs[i].equals("-log")) {
                for (int j = i + 1; j < strs.length; ++j) {
                    // -log���ֶ��
                    if (strs[j].equals("-log")) {
                        return false;
                    }
                }
                break;
            }
            // û�ҵ�-log
            if (i == strs.length - 1) {
                return false;
            }
        }
        for (int i = 1; i < strs.length; ++i) {
            if (strs[i].equals("-out")) {
                for (int j = i + 1; j < strs.length; ++j) {
                    // -out���ֶ��
                    if (strs[j].equals("-log")) {
                        return false;
                    }
                }
                break;
            }
            // û�ҵ�-out
            if (i == strs.length - 1) {
                return false;
            }
        }
        // ÿ�ֲ���-date -type -province������һ��
        for (int i = 1; i < strs.length; ++i) {
            // ����-date
            if (strs[i].equals("-date")) {
                for (int j = i + 1; j < strs.length; ++j) {
                    // -date���ֶ��
                    if (strs[j].equals("-date")) {
                        return false;
                    }
                }
                break;
            }
        }
        for (int i = 1; i < strs.length; ++i) {
            // ����-type
            if (strs[i].equals("-type")) {
                for (int j = i + 1; j < strs.length; ++j) {
                    // -type���ֶ��
                    if (strs[j].equals("-type")) {
                        return false;
                    }
                }
                break;
            }
        }
        for (int i = 1; i < strs.length; ++i) {
            // ����-province
            if (strs[i].equals("-province")) {
                for (int j = i + 1; j < strs.length; ++j) {
                    // -province���ֶ��
                    if (strs[j].equals("-province")) {
                        return false;
                    }
                }
                break;
            }
        }
        return true;
    }

    void checkCommand(Statistic sta) {
        for (int i = 0; i < 4; ++i) {
            if (!arguments[0].checkError()) {
                System.exit(1);
            }
        }
        ((ProvinceArgument) arguments[4]).checkError(sta);
    }

}

abstract class BaseArgument {
    String name;
    String value;

    BaseArgument(String name) {
        this.name = name;
        value = "";
    }

    BaseArgument(String[] strs) {
        name = strs[0];
        value = "";
    }

    abstract boolean checkError();
}

class LogArgument extends BaseArgument {

    LogArgument(String name) {
        // TODO Auto-generated constructor stub
        super(name);
    }

    LogArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        if (strs.length > 2) {
            value = "*";
        }
        value = strs[1];
    }

    boolean checkError() {
        // -logֻ��һ������ֵ
        if (value.startsWith("*")) {
            System.out.println("-log����ֵ�����˳�����");
            return false;
        }
        // -log�Ĳ���ֵ��һ��Ŀ¼��·��
        File alog = new File(value);
        if (!alog.isDirectory()) {
            return false;
        }
        return true;
    }
}

class OutArgument extends BaseArgument {

    OutArgument(String name) {
        // TODO Auto-generated constructor stub
        super(name);
    }

    OutArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        if (strs.length > 2) {
            value = "*";
        }
        value = strs[1];
    }

    boolean checkError() {
        // -outֻ��һ������ֵ
        if (value.startsWith("*")) {
            System.out.println("-out����ֵ�����˳�����");
            return false;
        }
        // ������������ļ�·��û�к�׺ ���׺��Ϊ".txt" //���޸�ΪĬ��
        if (!value.endsWith(".txt")) {
            int x = value.lastIndexOf('.');
            if (x >= 0) {
                value = value.substring(0, x);
            }
            value += ".txt";
            System.out.println("����ļ���ʽ���󣬸�Ϊ" + value);
        }
        return true;
    }
}

class DateArgument extends BaseArgument {

    DateArgument(String name) {
        // TODO Auto-generated constructor stub
        super(name);
    }

    DateArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        if (strs.length > 2) {
            value = "*";
        }
        value += strs[1];
    }

    boolean checkError() {
        // -dateֻ��һ������ֵ
        if (value.startsWith("*")) {
            System.out.println("-date����ֵ�����˳�����");
            return false;
        }
        // ���ڸ�ʽ��� ����ƽ�������YYYY-MM-DD��ʽ
        if (!value.matches(
                "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]"
                        + "|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468]"
                        + "[048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)   \r\n(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]"
                        + "{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))"
                        + "|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))"
                        + "-02-29) \r\n")) {
            value = "lastdate";
            System.out.println("���ڸ�ʽ���󣬸�ΪĬ����������");
        }
        return true;
    }
}

class TypeArgument extends BaseArgument {

    String[] valueList;

    TypeArgument(String name) {
        // TODO Auto-generated constructor stub
        super(name);
    }

    TypeArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        value = "valueList";
        int n = strs.length - 1;
        // ����ֵֻ��Ϊip,sp,cure,dead �����Ǿͺ��Ըô�������
        for (int i = 1; i < strs.length; ++i) {
            if (!(strs[i].equals("ip") || strs[i].equals("sp") || strs[i].equals("cure") || strs[i].equals("dead"))) {
                strs[i] = "";
                --n;
                System.out.println("-type����ֵֻ��Ϊip,sp,cure,dead�����Ըô������ֵ");
            }
        }
        // ��ȫ��������� ����Ϊ����ȱʡ
        if (n == 0) {
            valueList = new String[] { "ip", "sp", "cure", "dead" };
            return;
        }
        valueList = new String[n];
        for (int i = 0, j = 1; i < n; ++i) {
            while (strs[j].equals("")) {
                ++j;
            }
            valueList[i] = strs[j];
        }
    }

    boolean checkError() {
        // ����ֵ�����ظ� ���ظ���ɾȥ
        int n = valueList.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (valueList[i].equals(valueList[j])) {
                    valueList[j] = valueList[n--];
                }
            }
        }
        String[] temp = new String[n];
        for (int i = 0; i < n; ++i) {
            temp[i] = valueList[i];
        }
        valueList = temp;
        return true;
    }
}

class ProvinceArgument extends BaseArgument {

    String[] valueList;

    ProvinceArgument(String name) {
        // TODO Auto-generated constructor stub
        super(name);
    }

    ProvinceArgument(String[] strs) {
        super(strs);
        // TODO Auto-generated constructor stub
        value = "valuelist";
        valueList = new String[strs.length - 1];
        for (int i = 1; i < strs.length; ++i) {
            valueList[i - 1] = strs[i];
        }
    }

    boolean checkError() {
        return true;
    }

    boolean checkError(Statistic sta) {
        // �����ʡ�ݴ��ڣ������������г�����
        for (int i = 0; i < valueList.length; ++i) {
            if (!sta.data.containsKey(valueList[i])) {
                value = "all";
                valueList = new String[0];
                System.out.println("-province������������Ĭ���г�����ʡ������");
            }
        }
        return true;
    }
}

class LogFiles {
    String lastDate;
    TreeSet<File> files;

    LogFiles(String path) {
        File logFile = new File(path);
        File[] temp = logFile.listFiles();
        files = new TreeSet<File>(new Comparator<File>() {
            @Override
            // �ļ����������� ��д�����ڲ���Comparator��compare()
            public int compare(File f0, File f1) {
                // TODO Auto-generated method stub
                String name0 = f0.getName();
                String name1 = f1.getName();
                // ������·��������αȽ�ʱ��ǰ��
                if (Integer.parseInt(name0.substring(0, 4)) < Integer.parseInt(name1.substring(0, 4))) {
                    return -1;
                }
                if (Integer.parseInt(name0.substring(0, 4)) > Integer.parseInt(name1.substring(0, 4))) {
                    return 1;
                }
                if (Integer.parseInt(name0.substring(5, 7)) < Integer.parseInt(name1.substring(5, 7))) {
                    return -1;
                }
                if (Integer.parseInt(name0.substring(5, 7)) > Integer.parseInt(name1.substring(5, 7))) {
                    return 1;
                }
                if (Integer.parseInt(name0.substring(8, 10)) < Integer.parseInt(name1.substring(8, 10))) {
                    return -1;
                }
                if (Integer.parseInt(name0.substring(8, 10)) > Integer.parseInt(name1.substring(8, 10))) {
                    return 1;
                }
                return 0;
            }

        });
        for (int i = 0; i < temp.length; ++i) {
            files.add(temp[i]);
        }

        lastDate = files.last().getName().substring(0, 10);
    }

    void readFiles(String date, Statistic sta) {
        // Ĭ����������
        if (date.equals("lastdate")) {
            for (File f : files) {
                LogFiles.statisFile(f, sta);
            }
            return;
        }
        // ��Ĭ������
        for (File f : files) {
            if (LogFiles.dateCompare(date, f.getName().substring(0, 10))) {
                LogFiles.statisFile(f, sta);
                continue;
            }
            break;
        }
    }

    // ͳ��ĳ����־�ļ�������
    static void statisFile(File f, Statistic sta) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("/")) {
                    continue;
                }
                LogFiles.statisLine(line, sta);
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // ������־�ļ��е�һ��
    /**
     * @param line
     * @param sta
     */
    static void statisLine(String line, Statistic sta) {
        String[] strs = line.split(" ");
        // ʡ�ݴ���
        if (!sta.data.containsKey(strs[0])) {
            return;
        }
        // 8����־��
        switch (strs[1]) {
        case "����":
            if (strs[2].equals("��Ⱦ����")) {
                sta.data.get(strs[0])[0] += Integer.parseInt(strs[3].replace("��", ""));
                break;
            }
            if (strs[2].equals("���ƻ���")) {
                sta.data.get(strs[0])[1] += Integer.parseInt(strs[3].replace("��", ""));
            }
            break;
        case "����":
            sta.data.get(strs[0])[0] -= Integer.parseInt(strs[2].replace("��", ""));
            sta.data.get(strs[0])[3] += Integer.parseInt(strs[2].replace("��", ""));
            break;
        case "����":
            sta.data.get(strs[0])[0] -= Integer.parseInt(strs[2].replace("��", ""));
            sta.data.get(strs[0])[2] += Integer.parseInt(strs[2].replace("��", ""));
            break;
        case "�ų�":
            sta.data.get(strs[0])[1] -= Integer.parseInt(strs[3].replace("��", ""));
            break;
        case "���ƻ���":
            if (strs[2].equals("����")) {
                sta.data.get(strs[0])[1] -= Integer.parseInt(strs[4].replace("��", ""));
                sta.data.get(strs[3])[1] += Integer.parseInt(strs[4].replace("��", ""));
                break;
            }
            if (strs[2].equals("ȷ���Ⱦ")) {
                sta.data.get(strs[0])[1] -= Integer.parseInt(strs[3].replace("��", ""));
                sta.data.get(strs[0])[0] += Integer.parseInt(strs[3].replace("��", ""));
            }
            break;
        case "��Ⱦ����":
            if (strs[2].equals("����")) {
                sta.data.get(strs[0])[0] -= Integer.parseInt(strs[4].replace("��", ""));
                sta.data.get(strs[3])[0] += Integer.parseInt(strs[4].replace("��", ""));
            }
            break;
        default:
            break;
        }
    }

    // �Ƚ�����YYYY-MM-DD�����ַ��� ��date0���ڵ���date1 �򷵻�true
    static boolean dateCompare(String date0, String date1) {
        // ������·��������αȽ�ʱ��ǰ��
        if (Integer.parseInt(date0.substring(0, 4)) < Integer.parseInt(date0.substring(0, 4))) {
            return false;
        }
        if (Integer.parseInt(date0.substring(0, 4)) > Integer.parseInt(date1.substring(0, 4))) {
            return true;
        }
        if (Integer.parseInt(date0.substring(5, 7)) < Integer.parseInt(date1.substring(5, 7))) {
            return false;
        }
        if (Integer.parseInt(date0.substring(5, 7)) > Integer.parseInt(date1.substring(5, 7))) {
            return true;
        }
        if (Integer.parseInt(date0.substring(8, 10)) < Integer.parseInt(date1.substring(8, 10))) {
            return true;
        }
        if (Integer.parseInt(date0.substring(8, 10)) > Integer.parseInt(date1.substring(8, 10))) {
            return true;
        }
        return true;
    }
}

class Statistic {
    Map<String, int[]> data;

    public Statistic() {
        // TODO Auto-generated constructor stub
        data = new HashMap<String, int[]>();
        data.put("ȫ��", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("�㶫", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("�ӱ�", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("������", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("���ɹ�", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("�ຣ", new int[] { 0, 0, 0, 0 });
        data.put("ɽ��", new int[] { 0, 0, 0, 0 });
        data.put("ɽ��", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("�Ϻ�", new int[] { 0, 0, 0, 0 });
        data.put("�Ĵ�", new int[] { 0, 0, 0, 0 });
        data.put("���", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("�½�", new int[] { 0, 0, 0, 0 });
        data.put("����", new int[] { 0, 0, 0, 0 });
        data.put("�㽭", new int[] { 0, 0, 0, 0 });
    }

    void outPutFile(String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
            for(String keytemp:data.keySet()) {
                int[] valuetemp=data.get(keytemp);
                String out=keytemp;
                bw.write(keytemp+" ��Ⱦ����"+valuetemp[0]+"�� ���ƻ���"+valuetemp[1]+"�� ����"+valuetemp[2]+"�� ����"+valuetemp[3]+"��");
                bw.newLine();
            }
            bw.write("// ���ĵ�������ʵ���ݣ���������ʹ��");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}