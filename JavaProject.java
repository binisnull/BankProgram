/*은행 계좌 관리 프로그램
 * 기능 1: 계좌 개설 >> 계좌번호는 본인이 개설하도록 함
 * 기능 2: 입금
 * 기능 3: 출금
 * 기능 4: 전체고객 잔액조회
*/

import java.util.Scanner;

class Buildaccount{ //계좌 개설

    Scanner scan = new Scanner(System.in);

    public static long saveaccout[]=new long[999];
    public static String savename[]=new String[999];
    public static long savemoney[]=new long[999];
    
    long accountnum=0; //계좌번호(10자리)
    String name; //이름
    long Startdeposit=0; //초기입금액
    int choose=0;

    public Buildaccount(){ //생성자>>초기화해주기
        this.accountnum=0;
        this.name=null;
        this.Startdeposit=0;
        this.choose=0;

    } 

    public int Getnum(int choose){ //옵션 선택 후 선택 번호 반환
                
                while(true){
                    if(choose>0 && choose<6)
                        break;
                }

                return choose;
            }

    public void Getthings(){ //계좌번호, 이름, 초기입금액을 입력받는 메소드
        
        System.out.println("\n[계좌 개설]");
        
        Loop1:
        while(true){
           
            System.out.print("\n-계좌번호 입력: "); //중복 검사 어떻게? main에 생성된 각 계좌들을 배열에 넣을까??
            accountnum=scan.nextLong();
           
            if(!(accountnum>999999999 && accountnum<9999999999L)){
                System.out.println("\n<10자리로 입력해주세요>");
            }

            else{
                
                for(int i=0;saveaccout[i]!=0;i++){
                    if(accountnum==saveaccout[i]){
                    System.out.println("\n[이미 존재하는 계좌번호입니다]");
                    continue Loop1;
                    }
                }
                break Loop1;
             }
        }

        System.out.print("\n-이름 입력: ");
        name=scan.next();
       
        while(true){
            System.out.print("\n-초기 입금액 입력: ");
            Startdeposit=scan.nextInt();

            if(Startdeposit>=0){
                System.out.println("\n[계좌가 생성되었습니다]\n");
                break;
            }
            else
                System.out.println("\n<초기 입금액은 0원 이상으로 입력해주세요>\n");
        }

    }
    
    public void deposit(){ //입금
        int index=-1;
        long account=0;
        long wantDesposit=0;

        System.out.println("\n[-----입금-----] ");
                    
        System.out.print("\n-계좌번호 입력: ");
        account=scan.nextLong();
        
        for(int i=0;i<999;i++){ //입력받은 계좌번호에 해당하는 인덱스 찾아주기
            if(account==saveaccout[i]){
                index=i;
            }
        }

        if(index==-1){ //계좌번호가 없다면 존재하지 않는 계좌번호라는 멘트 출력
            System.out.println("\n[존재하지 않는 계좌번호입니다]\n");
        }
    
        else{//계좌번호 존재한다면 금액 바꿔주기
            
            System.out.print("\n-입금액 입력: ");
            wantDesposit=scan.nextLong();
            
            savemoney[index]=savemoney[index]+wantDesposit;  
            System.out.println("\n[입금을 완료했습니다]\n");
        }
    } 

    public void withdarw(){ //출금
               
        int index=-1;
        long account=0;
        long wantWithdraw=0;
        
        System.out.println("\n[-----출금-----] ");
                    
        System.out.print("\n-계좌번호 입력: ");
        account=scan.nextLong();
        
        for(int i=0;i<999;i++){
            if(account==saveaccout[i]){
                index=i;
            }
        }
        if(index==-1){ //계좌번호가 없다면 존재하지 않는 계좌번호라는 멘트 출력
            System.out.println("\n[존재하지 않는 계좌번호입니다]\n");
        }
       
        else{
            
            System.out.print("\n-출금액 입력: ");
            wantWithdraw=scan.nextLong();

            if(savemoney[index]-wantWithdraw<0){
                System.out.println("\n[잔액이 부족합니다]\n");
            }
                
            else{
                savemoney[index]=savemoney[index]-wantWithdraw;
                System.out.println("\n[출금을 완료했습니다]\n");
            }
    }
 
    }

    public void loadmember(int number){
        
        System.out.print("\n\n[계좌정보"+(number+1)+"]");
        System.out.print("\n-계좌번호: "+saveaccout[number]);
        System.out.print("\n-이름: "+savename[number]);
        System.out.print("\n-잔액: "+savemoney[number]+"\n\n");


    }

    public void Bankmenu(){ //메뉴출력
            System.out.println("-----MENU-----");
            System.out.println("1. 계좌 개설");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 계좌 정보 전체 출력");
            System.out.println("5. 프로그램 종료");
            }
    }

    class JavaProject{
        public static void main(String[] args){
            
            Scanner scan = new Scanner(System.in);
            Buildaccount build = new Buildaccount();
            
            int turn=0;
            int choosenum=0;
    
            while(true){
                build.Bankmenu();
                System.out.print("\n옵션을 선택해주세요: ");
                choosenum=scan.nextInt();
                
                if(build.Getnum(choosenum)==5){ //5번을 선택한 경우
                    System.out.println("\n[프로그램을 종료합니다]");
                    break;
                }
                
                else if(build.Getnum(choosenum)==1){ //1번을 선택한 경우
                    
                    build.Getthings();
                    build.saveaccout[turn]=build.accountnum;
                    build.savename[turn]=build.name;
                    build.savemoney[turn]=build.Startdeposit;
                    
                    turn++;

                    continue;
                }
    
                else if(build.Getnum(choosenum)==2){ //2번 선택
                   
                    build.deposit();
    
                    continue;
                }

                else if(build.Getnum(choosenum)==3){ //3번 선택
                    
                    build.withdarw();

                    continue;
                }

                else if(build.Getnum(choosenum)==4){ //4번 선택
                    
                    if(turn==0){
                        System.out.println("\n[등록된 계좌가 없습니다]");
                        
                        continue;
                    }

                    else{
                        for(int i=0;i<turn;i++){
                            build.loadmember(i);
                        }

                        continue;
                    }
                }
            }
            scan.close();
        }
    }
