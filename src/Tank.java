public class Tank {
    private int groundHeight;
    private int height;
    private int width;
    private int length;

    private int loadedHeight;

    public Tank(){
        this.groundHeight = 0;
        this.height = 0;
        this.width = 0;
        this.length = 0;
        this.loadedHeight = 0;
    }

    public int getTotalHeight(){
        return (groundHeight + height);
    }

    public void setGroundHeight(int groundHeight) {
        this.groundHeight = groundHeight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int calcLoadedVolume(int calcHeight){
        calcLoadHeight(calcHeight);
        return (loadedHeight*width*length);
    }

    public int calcVolume(){
        return (height*width*length);
    }

    private void calcLoadHeight(int calcHeight){
        if(calcHeight > groundHeight) {
            loadedHeight = calcHeight - groundHeight;
            if (loadedHeight > height)
                loadedHeight = height;
        }
        else
            loadedHeight = 0;
    }
}
