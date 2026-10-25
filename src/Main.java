final class Balance{
    private final Currency cur;
    private final double amt;

    Balance(){
        this.cur = Currency.RUB;
        this.amt = 0;
    }

    Balance(double amt){
        this.cur = Currency.RUB;
        this.amt = amt;
    }

    Balance(Currency cur){
        this.cur = cur;
        this.amt = 0;
    }

    Balance(Currency cur, double amt){
        this.cur = cur;
        this.amt = amt;
    }

    Balance add(int amt_new){
        if (amt_new < 0)
        {
            System.out.printf("ERROR! Can't add negative!");
            System.exit(-1);
        }
        Balance result = new Balance(this.cur, this.amt+amt_new);
        return result;
    }

    Balance subtract(int amt_new){
        if (amt_new < 0)
        {
            System.out.printf("ERROR! Can't add negative!");
            System.exit(-1);
        }
        if (this.amt <= 0)
        {
            System.out.printf("ERROR! Not enough!");
            System.exit(-1);
        }
        Balance result = new Balance(this.cur, this.amt-amt_new);
        return result;
    }

    Balance convert(Currency cur2){
        double t = this.amt*this.cur.getPriceInRublesKopecks();
        double amt = t/cur2.getPriceInRublesKopecks();
        return new Balance(cur2, amt);
    }

    void show(){
        System.out.print("Current balance: " + amt + cur.getSymbol() + "\n");
    }
}

final class BankAccaunt{
    private Balance bal;
    private int amt_all_op = 0;
    private int min_amt = 200;

    BankAccaunt(){
        this.bal = new Balance();
    }

    BankAccaunt(Balance bal){
        this.bal = bal;
    }

    BankAccaunt(int start_amt){
        this.bal = new Balance(start_amt);
    }

    BankAccaunt exchange(BankAccaunt bk, int amt){
        if(amt >= min_amt)
        {
            bk.bal = bk.bal.subtract(amt);
            this.bal = this.bal.add(amt);
            amt_all_op+=amt;
            bk.amt_all_op+=amt;
        }
        return this;
    }

    void show()
    {
        bal.show();
        System.out.print("Amount of all operations: "+amt_all_op+"\n\n");
    }
}

public class Main{
    public static void main(String[] args) {
        BankAccaunt t1 = new BankAccaunt(5000);
        BankAccaunt t2 = new BankAccaunt(10000);

        t1.show();
        t1.exchange(t2,500);
        t1.show();
        t2.show();
    }
}