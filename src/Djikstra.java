import java.util.PriorityQueue;

public class Djikstra {
    static int maxVal = Integer.MAX_VALUE; // 무한대를 표현해 주기 위해 최대값을 사용, 자주 사용해줄 거라 변수로 선언
    static int[] closetDis = {maxVal, maxVal, maxVal, maxVal, maxVal}; // 가장 가까운 값을 찾기 위한 일차원 배열
    static int[][] firstDis = {{0, 10, 3, maxVal, maxVal}, {maxVal, 0, 1, 2, maxVal, maxVal}, {maxVal, 4, 0, 8, 2}, {maxVal, maxVal, maxVal, 0, 7}, {maxVal, maxVal, maxVal, 9, 0}}; //초기에 주어진 조건을 저장해놓은 이차원 배열
    static String[] S = new String[5]; // 정렬이 완료되고 노드들을 넣어줄 배열


    public static void main(String[] args) {
        System.out.println("dijkstra's algorithm으로 계산한 결과는 다음과 같습니다.");
        dijkstra(0); //함수 호출
    }

    public static void dijkstra(int first) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(); // priority로 노드를 가까운 거리별로 정렬
        closetDis[first] = 0; // A에서 A 즉, 처음 시작인 A는 거리가 가장 가까운 0으로 초기화 해준다.
        int tempIdx = 0; // S배열에 정렬된 노드를 넣어주기 위해 임시로 만들어준 index 변수

        pq.offer(new Node("A", first, closetDis[first])); // pq에 시작하는 A를 넣어준다.

        while (!pq.isEmpty()) {
            int distanceNew = pq.peek().getDistance(); // distanceNew에 현재 위치에서 초기화 되어있는 거리를 저장해준다.
            int indexNow = pq.peek().getIndex(); // 현재 내가 보고 있는 노드(의 인덱스)를 저장해준다.
            String nameNow = pq.peek().getName(); // 내가 보고있는 노드의 이름을 저장해준다.
            pq.poll(); // 확인한 가장 위에 원소 삭제

            if (distanceNew > closetDis[indexNow]) {
                continue; // 만약 현재 받아온 거리가 내가 현재 보고 있는 노드와의 거리보다 크면 돌아볼 필요 없이 다음으로 넘어가면 된다.
            }

            S[tempIdx] = nameNow; // S 배열에 그 다음 가까운 노드(이름)를 넣어준다.
            System.out.println("───────────────────────────");
            System.out.println("S[" + tempIdx + "] : d[" + nameNow + "] = " + distanceNew);
            System.out.println("---------------------------------------------------");

            /*for문을 돌면서 다른 노드들 과의 거리를 계속 비교하여 순서를 수정해준다*/
            for (int i = 0; i < 5; i++) {
                int temp = closetDis[i]; // 출력문(결과화면)을 만들기 위해 임시로 저장해둔 변수

                /*만약 현재 거리가 0(나로부터 나까지의 거리)이 아니고, 현재 가까운 거리라고 저장되어 있는 거리보다 더 가깝고, 그 가까운 거리가 0 이상일 때*/
                if (firstDis[indexNow][i] != 0 && closetDis[i] > closetDis[indexNow] + firstDis[indexNow][i] && closetDis[indexNow] + firstDis[indexNow][i] > 0) {
                    closetDis[i] = closetDis[indexNow] + firstDis[indexNow][i]; // 그 위치까지의 가까운 거리를 바꿔주고

                    // pq에 새로운 노드를 추가해 (compareTo 함수를 재정의해서 필요에 맞게 자동으로 정렬 됨)순서대로 쌓일 수 있게 해준다.
                    switch (i) {
                        case 0:
                            pq.offer(new Node("A", i, closetDis[i]));
                            break;
                        case 1:
                            pq.offer(new Node("B", i, closetDis[i]));
                            System.out.println("d[B] = " + temp + " -> d[B] = " + closetDis[i]);
                            break;
                        case 2:
                            pq.offer(new Node("C", i, closetDis[i]));
                            System.out.println("d[C] = " + temp + " -> d[C] = " + closetDis[i]);
                            break;
                        case 3:
                            pq.offer(new Node("D", i, closetDis[i]));
                            System.out.println("d[D] = " + temp + " -> d[D] = " + closetDis[i]);
                            break;
                        case 4:
                            pq.offer(new Node("E", i, closetDis[i]));
                            System.out.println("d[E] = " + temp + " -> d[E] = " + closetDis[i]);
                            break;
                    }
                } else {
                    // 출력문을 위한 switch, 알고리즘에는 필요없음.
                    if (temp > distanceNew) {
                        switch (i) {
                            case 1:
                                System.out.println("d[B] = " + temp);
                                break;
                            case 2:
                                System.out.println("d[C] = " + temp);
                                break;
                            case 3:
                                System.out.println("d[D] = " + temp);
                                break;
                            case 4:
                                System.out.println("d[E] = " + temp);
                                break;
                        }
                    }
                }
            }
            System.out.println();
            tempIdx++; // 다음 가까운 노드를 다음 순서에 저장해주기 위해 tempIdx를 하나 올려준다.
        }
    }
}

/*새로 정의해준 Node Class. 노드 이름, index, 거리를 갖는다.*/
class Node implements Comparable<Node> {
    String name;
    int index;
    int distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node(String name, int index, int distance) {
        this.name = name;
        this.index = index;
        this.distance = distance;
    }

    /*노드의 거리를 비교해 정렬해주기 위해 Override한 compareTo 함수*/
    @Override
    public int compareTo(Node t) {
        if (distance > t.distance) {
            return 1;
        } else if (distance < t.distance) {
            return -1;
        }
        return 0;
    }
}