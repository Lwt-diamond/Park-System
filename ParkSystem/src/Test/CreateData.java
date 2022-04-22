package Test;

public class CreateData {
    public static void main(String[] args) {
        createParkSource();
    }
    public  static void createParkSource(){
        for(int i=0;i<3;i++){
            for(int j=1;j<=5;j++){
                char t = (char) ('A'+i);
                String sql = "insert into parkSource values('"+t+j+"','"+t+"区"+j+"号','false')";
                System.out.println(sql);
            }
        }
    }

}
/*
停车位模拟数据
insert into parkSource values('A1','A区1号','false')
insert into parkSource values('A2','A区2号','false')
insert into parkSource values('A3','A区3号','false')
insert into parkSource values('A4','A区4号','false')
insert into parkSource values('A5','A区5号','false')
insert into parkSource values('B1','B区1号','false')
insert into parkSource values('B2','B区2号','false')
insert into parkSource values('B3','B区3号','false')
insert into parkSource values('B4','B区4号','false')
insert into parkSource values('B5','B区5号','false')
insert into parkSource values('C1','C区1号','false')
insert into parkSource values('C2','C区2号','false')
insert into parkSource values('C3','C区3号','false')
insert into parkSource values('C4','C区4号','false')
insert into parkSource values('C5','C区5号','false')
 */
/*
停车场基本信息
select parkSource.SourceNo,parkSource.sourcePosition,parkSource.sourceIsUsed
from
parkSource
 */