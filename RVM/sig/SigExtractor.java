package sig;

import reg.RegHelper;

import java.io.File;
import java.util.HashMap;

/**
 * Created by xiaohe on 12/3/14.
 */
public class SigExtractor {
    public static final String SELECT = "select";
    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String SCRIPT_START = "script_start";
    public static final String SCRIPT_END = "script_end";
    public static final String SCRIPT_SVN = "script_svn";
    public static final String SCRIPT_MD5 = "script_md5";
    public static final String COMMIT = "commit";

    public static HashMap<String, Integer[]> TableCol = initTableCol();

    private static HashMap<String, Integer[]> initTableCol() {
        HashMap<String, Integer[]> tmp = new HashMap<>();
        //the arg types can be inferred from the signature file
        Integer[] argTy4Insert = new Integer[]{RegHelper.STRING_TYPE, RegHelper.STRING_TYPE,
                RegHelper.STRING_TYPE, RegHelper.STRING_TYPE};
        Integer[] argTy4Script = new Integer[]{RegHelper.STRING_TYPE};
        Integer[] argTy4ScriptSVN = new Integer[]{RegHelper.STRING_TYPE, RegHelper.STRING_TYPE,
                RegHelper.STRING_TYPE, RegHelper.INT_TYPE, RegHelper.INT_TYPE};
        Integer[] argTy4ScriptMD5 = new Integer[]{RegHelper.STRING_TYPE, RegHelper.STRING_TYPE};
        Integer[] argTy4Commit = new Integer[]{RegHelper.STRING_TYPE, RegHelper.INT_TYPE};

        tmp.put(SELECT, argTy4Insert);
        tmp.put(INSERT, argTy4Insert);
        tmp.put(UPDATE, argTy4Insert);
        tmp.put(DELETE, argTy4Insert);
        tmp.put(SCRIPT_START, argTy4Script);
        tmp.put(SCRIPT_END, argTy4Script);
        tmp.put(SCRIPT_SVN, argTy4ScriptSVN);
        tmp.put(SCRIPT_MD5, argTy4ScriptMD5);
        tmp.put(COMMIT, argTy4Commit);
        return tmp;
    }


    public static HashMap<String, Integer[]> extractMethoArgsMappingFromSigFile(File f) {
        //fake method at the moment, needs to be implemented.
        return TableCol;
    }
}
