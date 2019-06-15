class Solution{
    public String decodeString(String s) {
        Stack<Exp> stack = new Stack<>();
 
        Exp e = new Exp(1);
        stack.push(e);
 
        String num = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num + c;
            } else if (c == '{') { // match the {}
                if (num.length() == 0)
                    continue;
 
                int value = Integer.parseInt(num);
                num = "";
                Exp exp = new Exp(value);
                stack.push(exp);
            } else if (c == '}') { // match the {}
                Exp top = stack.pop();
                stack.peek().list.add(top);
            } else {
                stack.peek().list.add(new Exp(c));
            }
        } 
        Exp root = stack.pop();
         return root.getStr();
    }
}
 
class Exp {
    int count;
    List<Exp> list;
    char c; 
    public Exp(int num) {
        count = num;
        list = new ArrayList<>();
    }
    public Exp(char ch) {
        c = ch;
    }
 
    public String getStr() {
        String result = ""; 
        if (list != null) {
            for (int i = 0; i < count; i++) {
                for (Exp e : list) {
                    result += e.getStr();
                }
            }
        } else {
            result += c;
        }
         return result;
    }
}
