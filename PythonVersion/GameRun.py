import numpy as np

def lmove(r, c):
    dest = [[r - 2, c - 1],
            [r - 2, c + 1],
            [r - 1, c - 2],
            [r - 1, c + 2],
            [r + 1, c - 2],
            [r + 1, c + 2],
            [r + 2, c - 1],
            [r + 2, c + 1]]
    for d in dest:
        yield d

def update(color):
    h, w = color.shape
    score = np.zeros([h, w])
    for r in range(h):
        for c in range(w):
            v = 0
            for dr, dc in lmove(r, c):
                if 0 <= dr < h and 0 <= dc < w and color[dr, dc] == -1:
                    v += 1
            score[r, c] = v
    return score

def greedy(color, sr, sc):
    cr = sr
    cc = sc
    step = 0
    while color[cr, cc] == -1:
        color[cr, cc] = step
        score = update(color)
        candidates = []
        for dr, dc in lmove(cr, cc):
            if 0 <= dr < h and 0 <= dc < w and color[dr, dc] == -1:
                candidates.append([dr, dc, score[dr, dc]])
        if len(candidates) == 0:
            break
        candidates = sorted(candidates, key=lambda entry: entry[2]) 
        cr = candidates[0][0]
        cc = candidates[0][1]
        step += 1
    return color

def main(h, w, sr, sc):
    color = - 1 * np.ones([h, w], dtype=np.int8)
    color = greedy(color, sr, sc)
    final = np.amax(color) + 1
    print(color)
    print('Final score is: %d' % final)
    return final
    
if __name__ == '__main__':
    
    h = 7  # height of tiles
    w = 7
    sr = 5  # start coordinate
    sc = 1
    
    success = np.zeros([h, w], dtype=np.int8)
    for x in range(w):
        for y in range(h):
            final = main(h, w, x, y)
            if final == w * h:
                success[y, x] = 1 
    
            
    

