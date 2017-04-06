package univ.lecture.riotapi.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Calculator application
 */

public class CalcApp {
    private String[] args = new String[100];
	private ArrayList<String> li;
	private String oB = "(";
	private String cB = ")";
    private int size = 0;

	public CalcApp(String args) {
        StringTokenizer tokens = new StringTokenizer(args, " ");
        String[] temp = new String[100];

        for (int i = 0; tokens.hasMoreElements(); i++)
        {
            temp[i] = tokens.nextToken();
            this.size++;
        }

        this.args = new String[this.size];
        for (int i = 0; i < this.size; i++)
        {
            this.args[i] = temp[i];
        }
    }

	public double calc3() {
        String[] tokens = this.args;
		li = new ArrayList<>();
		li.addAll(Arrays.asList(tokens));

		while (li.size() > 2) {
			if (li.get(0).equals(oB) || li.get(2).equals(oB)) {
				if (li.get(0).equals(oB)) {
					li.remove(0);
					bracket(0);
				} else if (li.get(2).equals(oB)) {
					li.remove(2);
					bracket(2);
				}
			} else {
				String[] token = new String[3];
				token[0] = li.remove(0);
				token[1] = li.remove(0);
				token[2] = li.remove(0);
				li.add(0, String.valueOf(calc(token)));
			}
		}
		return Double.parseDouble(li.get(0));
	}

	public void bracket(int index) {

		int i = index;

		while ((i + 1) < li.size()) {
			// 여는 괄호
			// TO DO Array bug fix
			if (li.get(i + 1).equals(cB)) {
				if (li.get(i + 1).equals(cB)) {
					li.remove(i + 1);
					return;
				}
			}
			// 닫는 괄호
			else if (li.get(i).equals(oB) || li.get(i + 2).equals(oB)) {
				if (li.get(i).equals(oB)) {
					li.remove(i);
					bracket(i);
				} else if (li.get(i + 2).equals(oB)) {
					li.remove(i + 2);
					bracket(i + 2);
				}
			} else {
				String[] token = new String[3];
				token[0] = li.remove(i);
				token[1] = li.remove(i);
				token[2] = li.remove(i);
				li.add(i, String.valueOf(calc(token)));
			}
		}
		return;
	}

	public double calc(String[] tokens) {
		final double firstOperand;
		final double secondOperand;
		firstOperand = Double.parseDouble(tokens[0]);
		if (tokens.length > 2) {
			secondOperand = Double.parseDouble(tokens[2]);
		} else {
			secondOperand = Double.parseDouble(tokens[1]);
		}

		final Operator operator = Operator.findOperator(tokens[1]);

		return operator.evaluate(firstOperand, secondOperand);
	}
}
