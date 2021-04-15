import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException; 


// REMOVE import ARRAYS



public class CatTree implements Iterable<CatInfo>{
    public CatNode root;
    
    public CatTree(CatInfo c) {
        this.root = new CatNode(c);
    }
    
    private CatTree(CatNode c) {
        this.root = c;
    }
    
    
    public void addCat(CatInfo c)
    {
        this.root = root.addCat(new CatNode(c));
    }
    
    public void removeCat(CatInfo c)
    {
        this.root = root.removeCat(c);
    }
    
    public int mostSenior()
    {
        return root.mostSenior();
    }
    
    public int fluffiest() {
        return root.fluffiest();
    }
    
    public CatInfo fluffiestFromMonth(int month) {
        return root.fluffiestFromMonth(month);
    }
    
    public int hiredFromMonths(int monthMin, int monthMax) {
        return root.hiredFromMonths(monthMin, monthMax);
    }
    
    public int[] costPlanning(int nbMonths) {
        return root.costPlanning(nbMonths);
    }
    
    public Iterator<CatInfo> iterator()
    {
        return new CatTreeIterator();
    }
    
    
    class CatNode {
        
        CatInfo data;
        CatNode senior;
        CatNode same;
        CatNode junior;
        
        public CatNode(CatInfo data) {
            this.data = data;
            this.senior = null;
            this.same = null;
            this.junior = null;
        }
        
        public String toString() {
            String result = this.data.toString() + "\n";
            if (this.senior != null) {
                result += "more senior " + this.data.toString() + " :\n";
                result += this.senior.toString();
            }
            if (this.same != null) {
                result += "same seniority " + this.data.toString() + " :\n";
                result += this.same.toString();
            }
            if (this.junior != null) {
                result += "more junior " + this.data.toString() + " :\n";
                result += this.junior.toString();
            }
            return result;
        }


        public CatNode addCat(CatNode c) {
        	CatNode temp = new CatNode(this.data);
        	if (c.data.monthHired < this.data.monthHired) {
        		if (senior == null ) {
        			senior = c;
        		} else {
        			senior.addCat(c);	
        		}
        	}
        	
        	// If it is a more senior cat, check for if the position exists and if so, enter method again
        	
        	// Should we move the current senior to junior position
        	
        	if (c.data.monthHired > this.data.monthHired) {
        		 if (junior == null) {
        			junior = c;
        		} else {
        			junior.addCat(c);
        		}
        	}         
        	
        	// If it is a younger cat, check for if the position exists and if so, enter method again
        	
        	if (c.data.monthHired == this.data.monthHired) {
        		if (c.data.furThickness < this.data.furThickness) {
        			if (same == null) {
        				same = c;
        			} else {
        				same.addCat(c);
        			}
        			
        			// Check to see if furThickness is less and if not, the method is called again
        			
        		} else {
        				this.data = c.data;
        				c.same = this.same;
        				this.same = c;
        				c.data = temp.data;	
        			}
        		
        		// Check that this shallow copy at the end is okay
        		
        		// If the furThickness is lower, we store the node in a temp, fill the empty bucket with the c node
        		// and set the inserted node's "same" to the c node, which now holds all of the OG node's details
        		
        		}
        return this; 
        }
        
        
        private CatNode search(CatInfo c) {
        	if (this.data.equals(c)) {
        		return this;
        	} else {
        		if ((this.data.monthHired < c.monthHired) && (this.junior != null)) {
        			 return this.junior.search(c);
        		} else {
        			if ((c.monthHired < this.data.monthHired) && (this.senior != null)) {
            			this.senior.search(c);
        			} else {
        				if(this.same != null) {
        					return this.same.search(c);
        				}
        			}
        		}
        	}
        	return null;
        }
        

        public CatNode removeCat(CatInfo c) {     
        	CatNode foundNode = root.search(c);
        	if (foundNode == null) {
        		return this;
        	}
            if (foundNode.same != null) {
            	foundNode.data = foundNode.same.data;
            	foundNode.same = foundNode.same.same;
            	
            	// In the event that there is a same, the node to be deleted is filled with the data on the next Node
            	// and the same is set to the same of the same
            	
            } else {
            	
                   	
            	if (foundNode.senior != null) {
                	CatNode topJun = foundNode.junior;
                	foundNode.data = foundNode.senior.data;
                	if (foundNode.senior.junior != null) {
                    	CatNode travelSen = foundNode.senior.junior;
            			while (travelSen.junior != null) {
            				travelSen = travelSen.junior;
            			}
            			travelSen.junior = topJun;
            		}
                	foundNode.senior = foundNode.senior.senior;
                	
            		// If found senior doesn't equal null, we'll store the junior node and travel through the juniors of the Node
            		// to be deleted. Once at the end, we set the junior of the original node to be the junior of the senior's junior
            		// Probably something better than while loop
            		
            	} else {
            		if (foundNode.junior != null) {
            			foundNode.data = foundNode.junior.data;
            			foundNode.junior = foundNode.junior.junior;
            			foundNode.same = foundNode.junior.same;
            			// Assuming there is only a junior node, we say that the current deleted Node is equal
            			//  to the data in the junior node. We will then replace the .junior with the .junior.junior
            			// Also saves it in the case of there being a lingering same on the junior
            		} else {
            			foundNode.data = null;
            			foundNode = null;
            			return null;
            		}
            	}
            }
        	return this; 
        }
        
        
        public int mostSenior() {
        	if (this.senior == null) {
        		return this.data.monthHired;
        	}
            return this.senior.mostSenior();
        }
        
        public int fluffiest() {
        	if ((this.junior == null) || (this.senior == null)) {
        		return this.data.furThickness;
        	}
        	if (this.junior.fluffiest() > this.senior.fluffiest()) {
        		return junior.data.furThickness;
        	} else {
        		return this.senior.data.furThickness;
        	}
        }
        
        
        // We need to check all seniors and juniors

                    
        public int hiredFromMonths(int monthMin, int monthMax) {
        	int sum = 0;
        	if (monthMax < monthMin) {
        		return 0;
        	} 
        	if ((monthMin <= this.data.monthHired)&&(this.data.monthHired <= monthMax)) {
        			if (this.junior != null) {	
        				sum =+ 1 + this.junior.hiredFromMonths(monthMin, monthMax);
        				if (this.same != null) {
            				sum =+ this.same.hiredFromMonths(monthMin, monthMax);
        				} else {
        					return 1;
        				}
        			}
        			if (this.senior != null) {
        				sum =+ 1 + this.senior.hiredFromMonths(monthMin, monthMax);
        				if (this.same != null) {
            				sum =+ this.same.hiredFromMonths(monthMin, monthMax);
        				} else {
        					return 1;
        				}
        			}
        			if (this.same != null) {
        				sum =+ 1 + this.same.hiredFromMonths(monthMin, monthMax);
	        		} else {
	        			return 1;
	        		}      		
        	} else {
        		if ((this.data.monthHired < monthMax)&&(this.junior != null)) {
        			sum =+ this.junior.hiredFromMonths(monthMin, monthMax);
        			} else {
        				if ((monthMin < this.data.monthHired)&&(this.senior != null)) {
                			sum =+ this.senior.hiredFromMonths(monthMin, monthMax);
            			}
        			}
        		}
        	return sum;
        }
        
        public CatInfo fluffiestFromMonth(int month) {
        	if ((this.data.monthHired < month)&&(this.junior != null)) {
        		return this.junior.fluffiestFromMonth(month);
        	} else {
        		if ((month < this.data.monthHired)&&(this.senior != null)) {
        			return this.senior.fluffiestFromMonth(month);
        		} else {
        			if (this.data.monthHired == month) {
        				return this.data;
        			}
        		}
        	}
        	return null;
        }
          
        public int[] costPlanning(int nbMonths) {
        	int currentMonth = 243;
        	int [] prices = new int[nbMonths];
        	CatTree subTree = new CatTree(this); 	
            for (int i = 0; i < prices.length; i++) {
                CatTreeIterator iterator =  (CatTreeIterator) subTree.iterator();
            	while (iterator.hasNext()) {
            		CatInfo current = iterator.next();
            		if (current.nextGroomingAppointment == currentMonth) {
            			prices[i] += current.expectedGroomingCost;
            		}
            	}
            	currentMonth++;
            	nbMonths--;
            }
        	return prices;
        }        
    }
    
    private class CatTreeIterator implements Iterator<CatInfo> {
    	ArrayList<CatNode> list = new ArrayList<CatNode>(); 
        int curPos = 0;
        CatNode curNode = root;
    	
        // Create Iterator from instance of Tree?
        
        
        private void arrayBuilder(CatNode input) {
        	if (input.senior != null) {
        		arrayBuilder(input.senior);
        	}
        	if (input.same != null) {
    			arrayBuilder(input.same);
    			list.add(input);
        	} else {
    			list.add(input);
    		}
        	if (input.junior != null) {
        		arrayBuilder(input.junior);
        	}
        }
        	
        
        public CatTreeIterator() {
        	if (curNode == null) {
        		throw new IllegalArgumentException ("Cannot iterate through empty tree");
        	}
        	arrayBuilder(curNode);
        }
        
        public CatInfo next(){
            if (!hasNext()) {
                throw new NoSuchElementException ("There is no more elements to iterate through");
            }
        	CatNode tmp = list.get(curPos);
        	curPos++;
            return tmp.data;
        }

        public boolean hasNext() {
        	if (curPos != list.size()) { 
        		return true;
        	} else {
        		return false;
        	}
        }
    }
    public static void main(String args[]) {
    }
}