import java.util.ArrayList;
import java.util.Objects;

public class Document {


    ArrayList<String> docNames;
    ArrayList<String> mails;
    ArrayList<String> phoneNums;

    public Document(ArrayList<String> docNames, ArrayList<String> mails, ArrayList<String> phoneNums) {
        this.docNames = docNames;
        this.mails = mails;
        this.phoneNums = phoneNums;
    }

    public ArrayList<String> getDocNames() {
        return docNames;
    }

    public void setDocNames(ArrayList<String> docNames) {
        this.docNames = docNames;
    }

    public ArrayList<String> getMails() {
        return mails;
    }

    public void setMails(ArrayList<String> mails) {
        this.mails = mails;
    }

    public ArrayList<String> getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(ArrayList<String> phoneNums) {
        this.phoneNums = phoneNums;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docNames=" + docNames +
                ", mails=" + mails +
                ", phoneNums=" + phoneNums +
                '}';
    }
}
