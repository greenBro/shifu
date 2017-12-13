package yuangong.id.bean;

/**
 * Created by Mathy on 2016/9/19 0019.
 * 描述：
 */
public class ClearNumberBean extends BaseReturnBean{

    /**
     * ClearTotolCount : 3
     * ClearedCount : 4
     * ClearingCount : 7
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int ClearTotolCount;
        private int ClearedCount;
        private int ClearingCount;

        public int getClearTotolCount() {
            return ClearTotolCount;
        }

        public void setClearTotolCount(int ClearTotolCount) {
            this.ClearTotolCount = ClearTotolCount;
        }

        public int getClearedCount() {
            return ClearedCount;
        }

        public void setClearedCount(int ClearedCount) {
            this.ClearedCount = ClearedCount;
        }

        public int getClearingCount() {
            return ClearingCount;
        }

        public void setClearingCount(int ClearingCount) {
            this.ClearingCount = ClearingCount;
        }
    }
}
