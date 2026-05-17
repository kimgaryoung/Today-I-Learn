//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.awt.color.ICC_ColorSpace;
import java.util.Scanner;
import java.time.LocalDate;


 class Diet{

    Scanner scanner =new Scanner(System.in);

    LocalDate curDate=LocalDate.now();;// 오늘 날짜 -다이어트 클래스의 속성
     String name; // 사용자 이름



    Diet(boolean run) {


        if (run) {
            System.out.println("-----------[다이어트 앱]---------------");

            System.out.print("이름을 입력해주세요:");
            name = scanner.nextLine();
            System.out.println("저는 다이어트 코치 scarlett 입니다." + name + "반갑습니다.");


            System.out.println("오늘은 " + curDate + " 입니다.");

            System.out.print("다이어트를 시작하시겠습니까?(y/n):");
            String ans = scanner.nextLine();

            if (ans.equals("y")) {
                start(name);
            } else if (ans.equals("n")) {
                stop();
            } else {
                start(name);
                // 임시로 일단 뒤에 짜고 다시

            }

        }
    }


    void start(String name){// 시작한다고 누르면 오늘자 날짜가 db에 저장되면서 프로그램 진행되게 짜고 싶었습니다. 가정이고 실제 구현 X)

        System.out.println("다이어트를 시작합니다. 뾰로롱");


        //1. 체중 측정 진행
        Weight w =new Weight(name);
        System.out.println();

        while(true) {
            scanner.nextLine(); // 비워주지 않으니까 계속 입력 스킵하고 else로 넘어감.


            //2.운동할지 식단할지 선택(운동 갔다가 다시 여기로 돌아오기) -둘다 자식 클래스
            System.out.print(name + "님! 식단으로 갈까요? 운동으로 갈까요?(식단/운동):");

            String ans = scanner.nextLine().trim();

            if (ans.equals("식단")) {


            } else if (ans.equals("운동")) {
                Exercise exer = new Exercise(name, true);

            } else {
                    stop();

            }
        }



    }

    void stop() { // 종료되면 프로그램 그만
        System.out.println("오늘은 날이 아니군요.. 다음에 만나요. 프로그램을 종료합니다.");
        System.exit(0);

    }

}



class Weight extends Diet{
     float curKG;//현재 몸무게
     float goalKG;//목표몸무게
     float fatPercent;//체지방률
     float muscleKG;//근육량
     int baseKcal; //기초대사량



    Weight(String name) {

        super(false); // 그냥 상속 받으면 생성자 무한 반복하는 문제 생김.
        this.name=name; // 없으면 diet에서 이름이 안넘어옴


        System.out.println("----------"+curDate + " 체중 측정을 진행하겠습니다!-------------");
        System.out.print( "인바디와 체중계 중 어디서 측정하겠습니까?(인바디/체중계):");
        String measureT = scanner.nextLine().trim();//trim()공백 자동 제거 해줘서 띄워서 입력해도 괜춘

        if (measureT.equals("인바디")) {
            measure( curKG, fatPercent, muscleKG, baseKcal);

        } else if (measureT.equals("체중계")) {
            measure(curKG);

        } else {

            measure(curKG);// 뒤에 다 짜고 변경
        }
    }

    //몸무게 측정
    void measure(float curKG){//그냥 몸무게 측정 -오버로딩으로 사용하고 싶었습니다.

        String ans ="식단"; //체중 측정 후 이동할곳

        System.out.println("체중계 위로 올라가세요...");
        System.out.println("측정중...");
            try {

                //스레드로 3,2,1 카운트 되는 것 처럼
                Thread.sleep(1000);
                System.out.println("3");
                Thread.sleep(1000);
                System.out.println("2");
                Thread.sleep(1000);
                System.out.println("1");

                System.out.println("측정완료!");
                System.out.print(name+ "님의 몸무게를 입력하세요(ex 80.5):");

                curKG=scanner.nextFloat();



            }catch(InterruptedException e){
                System.out.println(name+ "의 아잇 체중계 고장 ..삐용삐용..");
                curKG=100;
            }


        System.out.print(name+"님의 목표 몸무게를 입력하세요(ex 76.5):");
        goalKG=scanner.nextFloat();

        System.out.println("목표 몸무게까지 :" + weightToloss(curKG,goalKG)+"Kg 남았습니다.");

        System.out.print("[enter]을 눌러 다음으로 이동하세요."); // 왜.. 갑자기 엔터 안치면 안넘어가지는 거지?..





    }

    //인바디 측정
    void measure(float curKG,float fatPercent,float muscleKG, int baseKcal){ //인바디 측정 -오버로딩으로 사용하고 싶었습니다.
        String ans ="식단"; //체중 측정 후 이동할곳

        System.out.println("체중계 위로 올라가세요...");
        System.out.println("측정중...");
        try {

            //스레드로 3,2,1 카운트 되는 것 처럼
            Thread.sleep(1000);
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1");

            System.out.println("측정완료! 숫자만 입력하세요!");
            System.out.print(name+ "님의 현재 몸무게를 입력하세요(ex 80.5kg):");
            curKG=scanner.nextFloat();

            System.out.print(name+ "님의 체지방률을 입력하세요(ex 28.5%):");
            fatPercent=scanner.nextFloat();

            System.out.print(name+ "님의 골격근량 입력하세요(ex 20.5kg):");
            muscleKG=scanner.nextFloat();

            System.out.print(name+ "님의 기초대사량 입력하세요(ex 1524kcal):");
            baseKcal=scanner.nextInt();



        }catch(InterruptedException e){
            System.out.println(name+ "의 아잇 체중계 고장 ..삐용삐용..");
            curKG=100;
            fatPercent=28.5f;
            muscleKG=20.5f;
            baseKcal=1524;
        }

        System.out.println("거의 다왔어요! 화이팅!!! 마지막으로...");
        System.out.print(name+"님의 목표 몸무게를 입력하세요(ex 76.5):");
        goalKG=scanner.nextFloat();

        System.out.printf("목표 몸무게까지 : %.2f Kg 남았습니다.%n",weightToloss(curKG,goalKG));
        System.out.println();






    }


    float weightToloss(float curKG,float goalKG){


        if(curKG-goalKG<=0)
        {
            return 0;
        }
        return curKG-goalKG;

    }


}


// 다이어트 <-운동
class Exercise extends Diet {

     int exerTime=0;
     int burnKcal=0;
     String exerPlace=" ";

    Exercise(String name,boolean run )
    {
        super(false);
        this.name = name;// 상속 받아와서 저장.

        if(run) {
            System.out.println("----------" + curDate + " 운동을 진행하겠습니다!-------------");// diet클래스 상속 받아서 curDate사용
            System.out.print(name + "님 유산소와 무산소중 어떤것을 수행하시겠습니까?(유산소/무산소):");
            String exertype = scanner.nextLine().trim();


            System.out.println("운동을 지금 시작하시겠습니까? 기록만 진행하시겠습니까?");
            System.out.print("(1)운동 시작 (2)운동 기록만: (1)or(2)");
            int record = scanner.nextInt();




            if (record == 1) { // 운동 시작

                if (exertype.equals("유산소")) {
                    start(name);
                    Cardio c = new Cardio(name, exerTime);
                    //다시 운동 식단 선택으로


                } else if (exertype.equals("무산소")) {
                    start(name);
                    Strength s = new Strength(name,exerTime);
                    //다시 운동 식단 선택으로

                }
            } else if (record == 2) {// 운동 기록만
                start(name);
                System.out.println();
                System.out.print("오늘 소비한 칼로리가 어떻게 될까요?(예 :300");
                burnKcal=scanner.nextInt();

                //다시 운동, 식단 선택으로

            } else {


                stop();

            }
        }



    }

    @Override
    void start(String name) {


        System.out.print("운동 시간을 설정 하세요. 분으로 기록하세요. (ex 90m):");
        exerTime= scanner.nextInt();
        scanner.nextLine();

        System.out.print("운동 장소를 적어주세요(ex 헬스장)");
        exerPlace= scanner.nextLine().trim();



    }


    @Override
    void stop(){

        if(exerTime==0)
        {
            System.out.print("운동 시간을 설정 하세요. 분으로 기록하세요. (ex 90m):");
            exerTime= scanner.nextInt();

        }

        if(exerPlace.equals(" "))
        {
            System.out.print("운동 장소를 적어주세요(ex 헬스장)");
            exerPlace= scanner.nextLine().trim();

        }


        System.out.print("소모 칼로리 (1)직접 입력 (2)자동 계산");

        int choose =scanner.nextInt();

        if(choose==1)
        {
            System.out.print("소모 칼로리를 입력해주세요.(ex 560)");
            burnKcal= scanner.nextInt();
        }
        else if(choose==2)
        {
            System.out.println(name+"님의 소모 칼로리는 대락"+ AutoKcal() +"로 예상됩니다.");
        }



    }

    int AutoKcal()// 소모한 칼로리 계산- 유산소, 무산소에 따라 다른게 계산
    {

        // 110~130 : 분당 7kcal
        //130~150 :분당 9kcal
        //150이상 : 분당 11kcal

        //시간 * 분당 칼로리
        return 0;
    }



}

//운동 <- 유산소
class Cardio extends Exercise {// 유산소 운동

    String exerName;// 운동이름
    int heartRate;// 심박수


    Cardio(String name, int exerTime) {

        super(name, false);
        this.exerTime = exerTime;


        System.out.println("--------------즐거운 유산소 운동 시작!!--------------");
        System.out.print(name + "님 진행할 운동 이름을 알려주세요. (ex 테니스): ");
        exerName = scanner.nextLine();

        System.out.println(name+"님의 운동이 종료되었습니다. "+ Face(exerTime)+ "의 평균 심박수를 보였습니다.");

        System.out.println(name+"님이 소모한 칼로리는  "+ AutoKcal()+ "Kcal 입니다. ");
        System.out.println("-----------운동 기록을 종료 합니다.------------");



    }

    int Face(int exerTime) {

        System.out.println("당신의 심박수 측정을 시작하겠습니다. (운동 시작!)");

        try {

            Thread.sleep(1000);
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1");

            Thread.sleep(2000);
            System.out.printf("%.1f 분 경과, 평균 심박수=120",exerTime * 1.0 / 3);
            System.out.println();


            Thread.sleep(2000);
            System.out.printf("%.1f 분 경과, 평균 심박수=154",exerTime * 2.0 / 3);
            System.out.println();

            Thread.sleep(2000);
            System.out.printf("%.1f 분 경과, 평균 심박수=134",exerTime * 3.0 / 3);
            System.out.println();
            heartRate = 134;// 심박수 하드 코딩..




        } catch (InterruptedException e) {
            System.out.println("유산소 스레드  오류 디버깅 가보자고 ~~ ");
            heartRate = 134;
        }
        return heartRate;
    }


    @Override
    int AutoKcal() { // 시간 * 심박수당 소모 칼로리 = 자동으로 소비한 칼로리 계산
        int minKcal=0;

        if(heartRate<130)
        {
            minKcal=7;
        }
        else if (heartRate<150) {
            minKcal=9;
        }
        else if(heartRate>=150) {
            minKcal=11;

        }


        return exerTime*minKcal;
    }
}


// 운동 <- 무산소
class Strength extends Exercise{// 무산소 운동


     String[] exerTarget;
     int setTotal;

     Strength(String name, int exerTime){
         super(name,false);
         this.name=name;
         this.exerTime=exerTime;

         int exerTargetNum=1;

         exerTarget=new String[]{"가슴", "등", "어깨", "팔 ", "하체" ," 복근"};

         System.out.println("-------------무산소 운동할 부위를 선택해주세요!---------------------");
         System.out.print("1.가슴, 2.등, 3.어깨, 4,팔 , 5,하체 ,6.복근: (숫자 선택) ");
         exerTargetNum=scanner.nextInt();




         System.out.print("몇세트 하시겠습니까?(ex 3세트) :");
         setTotal=scanner.nextInt();

         setCnt(setTotal);



         System.out.println(name+"님이 " + exerTarget[exerTargetNum-1]+"운동을 통해 소모한 칼로리는  "+ AutoKcal()+ "Kcal 입니다. ");
         System.out.print("[enter]을 눌러 다음으로 이동하세요.");


     }

     void setCnt(int setTotal){

         System.out.println("화이팅 !!!!!화이팅 !!! 곧 세트가 시작합니다. ");
         int setN=1;

         try {

             Thread.sleep(1000);
             System.out.println("3");
             Thread.sleep(1000);
             System.out.println("2");
             Thread.sleep(1000);
             System.out.println("1");


             while(setN<setTotal)
             {
                 Thread.sleep(2000);
                 System.out.println(setN+"개 세트 완료, 1분 쉬기 ");
                 setN++;

             }

             System.out.println(setTotal+"세트의 무산소 운동이 끝났습니다! 야호 ~");

         } catch (InterruptedException e) {
             System.out.println("무산소 오류 ~~ ");

         }



     }


      @Override
    int AutoKcal() { // 실제와 전혀 무관,, (세트수 당 칼로리 * 시간)
        int minKcal=0;

        if(setTotal>3)
        {
            minKcal=7;
        }
        else if (setTotal<5) {
            minKcal=9;
        }
        else if(setTotal>=5){
            minKcal=11;

        }


        return exerTime*minKcal;
    }

}



/*
class Food extends Diet{

}
*/












        public class Main {
            public static void main(String[] args) {
                //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
                // to see how IntelliJ IDEA suggests fixing it.
                //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
                // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.


                Diet diet = new Diet(true);

            }
        }


