package etc;

import java.util.ArrayList;

public class RiskStatus {
    private ArrayList<String> status;
    
    public RiskStatus() {
        status = new ArrayList<String>();
    }

    public void append(String str) {
        status.add(str);
    }
    
    public int size() {
        return status.size();
    }

    public void clear() {
        status.clear();
    }

    /**
     * Default toString() method to print out all statuses.
     * Use toString(String separator) to tweak the output
     * @return The printout of all the string in status line-by-line
     */
    public String toString() {
        return toString("\n");
    }

    /**
     * Add some tweakability to printout of status, for example:
     * toString("<br>") will append every string with <br> tag useful
     * for HTML
     */
    public String toString(String separator) {
        return wrap("",separator);
    }
    
    /**
     * Wrap each line with certain String and return all lines
     */
    public String wrap(String front, String back) {
        StringBuilder sb = new StringBuilder();
        for(String str : status) {
            sb.append(front).append(str).append(back);
        }
        return sb.toString();
    }
}
