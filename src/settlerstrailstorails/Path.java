package settlerstrailstorails;

public class Path{
    Rail[] rails=new Rail[0];
    Path(){
        
    }
    void setPath(Rail[] r){
        rails=new Rail[r.length];
        System.arraycopy(r, 0, rails, 0, r.length);
    }
    Rail[] getPath(){
        return rails;
    }
    void add(Rail r){
        Rail[] newR=new Rail[rails.length+1];
        System.arraycopy(rails, 0, newR, 0, rails.length);
        newR[newR.length-1]=r;
        rails=newR;
    }
    boolean contains(Rail r){
        for(int i=0;i<rails.length;i++){
            if(rails[i]==r)return true;
        }
        return false;
    }
    Rail getLast(){
        return rails.length>0 ? rails[rails.length-1] : null;
    }
    boolean isEqual(Path p){
        Rail[] other=p.getPath();
        if(other.length!=rails.length)return false;
        for(int i=0;i<rails.length;i++){
            if(rails[i]!=other[i])return false;
        }
        return true;
    }
    Rail removeFirst(){
        if(rails.length>0){
            Rail[] nRail = new Rail[rails.length-1];
            Rail retRail=rails[0];
            rails[0]=null;
            for(int i=1;i<rails.length;i++){
                nRail[i-1]=rails[i];
            }
            rails=nRail;
            return retRail;
        }else{
            return null;
        }
    }
}