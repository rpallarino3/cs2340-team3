package etc;

import java.util.*;

public class RiskStatus {
    public static int DEF_MAX_SIZE=100;
    private LinkedList<Object> status;
    private LinkedList<Long> addTime;
    private long printTime = 0l;
    private int size = 0;
    private int maxSize;
    private String oldOpenTag = "<p class=\"oldStatus\">", oldCloseTag = "";
    private String newOpenTag = "<p class=\"newStatus\">", newCloseTag = "";
    
    public RiskStatus() {
        this(DEF_MAX_SIZE);
    }

    public RiskStatus(int maxSize) {
        this.maxSize = maxSize;
        status = new LinkedList<Object>();
        addTime = new LinkedList<Long>();
    }
    
    public void wrapOld(String openTag, String closeTag) {
        this.oldOpenTag = openTag;
        this.oldCloseTag = closeTag;
    }

    public void wrapNew(String openTag, String closeTag) {
        this.newOpenTag = openTag;
        this.newCloseTag = closeTag;
    }
    
    /**
     * This append method is still iffy because of the possible time errors in the adding of the status, so some real testing may be needed. Because I cannot test for all the time interval.
     * It will add the status object as well as when it was added.
     */
    public void append(Object str) {
        if(size==maxSize){
            status.removeLast();
            addTime.removeLast();
        }
        status.addFirst(str);
        addTime.addFirst(System.nanoTime());
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
     * Add more tweakability to printout of status
     * 
     * --Greg
     */
    public String toString(String separator0, String separator1) {
        return wrap(separator0,separator1);
    }
    
    /**
     * Wrap each line with certain String and return all lines
     */
    private String wrap(String front, String back) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < status.size(); i++) {
            if(addTime.get(i) > printTime) { //wrap new
                sb.append(front).append(newOpenTag).append(status.get(i).toString()).append(newCloseTag).append(back);
            } else {
                sb.append(front).append(oldOpenTag).append(status.get(i).toString()).append(oldCloseTag).append(back);
            }
        }
        printTime=System.nanoTime();
        return sb.toString();
    }
}
